package com.en.sphzb.service;

import com.en.sphzb.entity.Cases;
import com.en.sphzb.entity.KeyWeight;
import com.en.sphzb.entity.Question;
import com.en.sphzb.entity.mapper.CaseMapper;
import com.en.sphzb.repository.CaseRepository;
import com.en.sphzb.repository.CaseTypeRepository;
import com.en.sphzb.repository.KeyWeightRepository;
import com.en.sphzb.repository.QuestionsRepository;
import com.en.sphzb.utils.CasesUtil;
import com.en.sphzb.utils.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 案件装换为题目
 * create by en
 * at 2019/2/25 14:16
 **/
@Service
@Slf4j
public class CaseToQuestionService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private KeyWeightRepository keyWeightRepository;

    /**
     * 每隔五个小时定时执行，将案件转换为题目。
     *  异步服务，可以在案件上传后调用
     */
    @Scheduled(cron = "0 0 0/5 * * ?")      //  定时任务，每隔五小时执行一次
//    @Async          // 异步调用注解，此方法可以进行异步调用
    public void parseQuestion() {
        log.info("【案件转换为题目】服务开始进行！");
        // 1. 查询所有权重值，加载至内存
        // 注释原因：无用数据过多，可能影响内存，直接进行数据库查询
        // List<KeyWeight> allKeyWeight = new CopyOnWriteArrayList<>(keyWeightRepository.findAll());

        // 2. 查询未转化成题目的案件数量，如果超过1000个，则进行分批处理
        Set<Long> caseToParseSet = caseMapper.findCaseToParse();
        // 3. 对案件案情进行分词，模糊时间、地点、人物
        for (long caseId : caseToParseSet) {
            Optional<Cases> caseOptional = caseRepository.findById(caseId);
            if (!caseOptional.isPresent()) {
                continue;
            }
            Cases aCase = caseOptional.get();
            String description = aCase.getCaseDescription();
            log.debug("【案件转换题目】案情：{}", description);
            // 模糊时间、身份证、手机号
            String questionContent = CasesUtil.replacePattern(description);
            // 4. 对案件案情进行分词
            String[] words = WordUtil.seg(CasesUtil.deletePattern(description),
                    SegmentationAlgorithm.FullSegmentation).split(" ");
            log.debug("【案件转换题目】分词结果:{}", Arrays.toString(words));
            // 5. 统计案件对应的案件类别的权重
            //  案件类别的最终权重结果
            Map<Integer, Double> caseTypeMap = new ConcurrentHashMap<>();
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                // 查询词语的对应的权重
                List<KeyWeight> allKeyWeight = keyWeightRepository.findAllByKeyContent(word);
                for (int i = 0; i < allKeyWeight.size(); i++) {
                    KeyWeight keyWeight = allKeyWeight.get(i);
                    String keyContent = keyWeight.getKeyContent();
                    if (keyContent.equals(word)) {
                        if (caseTypeMap.containsKey(keyWeight.getTypeId())) {
                            caseTypeMap.put(keyWeight.getTypeId(), caseTypeMap.get(keyWeight.getTypeId()) + keyWeight.getWeight());
                        } else {
                            caseTypeMap.put(keyWeight.getTypeId(), keyWeight.getWeight());
                        }
                    }
                }
            }
            // 6. 对权重结果进行排序，运用list进行排序
            List<CaseTypeFrequency> caseTypeFrequencyList = new ArrayList<>();
            for (Integer key : caseTypeMap.keySet()) {
                caseTypeFrequencyList.add(new CaseTypeFrequency(key, caseTypeMap.get(key)));
            }
            if (caseTypeFrequencyList.isEmpty()) {
                continue;
            }
            caseTypeFrequencyList.sort(new CaseTypeFrequency());
//            log.debug("【案件转换题目】权重排序后信息:{}", Arrays.toString(caseTypeFrequencyList.toArray()));
            // 7. 构建题目，保存
            Question question = new Question();
            question.setCaseId(caseId);
            question.setStatus(1);
            question.setQuestionContent(questionContent);
            question.setAnswerA(caseTypeRepository.findById(caseTypeFrequencyList.get(0).caseType).get().getTypeName());
            question.setAnswerB(caseTypeRepository.findById(caseTypeFrequencyList.get(1).caseType).get().getTypeName());
            question.setAnswerC(caseTypeRepository.findById(caseTypeFrequencyList.get(2).caseType).get().getTypeName());
            question.setAnswerD(caseTypeRepository.findById(caseTypeFrequencyList.get(3).caseType).get().getTypeName());
            questionsRepository.save(question);
        }
        log.info("【案件转换为题目】服务完成！");
    }

    /**
     * 案别类型及权重
     */
    class CaseTypeFrequency implements Comparable,Comparator {

        public CaseTypeFrequency() {
        }

        public CaseTypeFrequency(Integer caseType, Double frequency) {
            this.caseType = caseType;
            this.weightTotal = frequency;
        }

        private Integer caseType;

        private Double weightTotal;


        @Override
        public int compareTo(Object o) {
            if (o instanceof CaseTypeFrequency)
                return weightTotal.compareTo(((CaseTypeFrequency) o).weightTotal);
            else
                throw new RuntimeException();
        }

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof CaseTypeFrequency && o2 instanceof CaseTypeFrequency)
                return ((CaseTypeFrequency) o2).compareTo((o1));
            else
                throw new RuntimeException();
        }

        @Override
        public String toString() {
            return "CaseTypeFrequency{" +
                    "caseType=" + caseType +
                    ", weightTotal=" + weightTotal +
                    '}';
        }
    }

}
