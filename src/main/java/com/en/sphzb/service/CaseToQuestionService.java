package com.en.sphzb.service;

<<<<<<< HEAD
import com.en.sphzb.entity.Case;
import com.en.sphzb.entity.CaseType;
import com.en.sphzb.entity.Question;
=======
import com.en.sphzb.entity.Cases;
>>>>>>> 2991ad825f826d5057bab562fe48c2ce836f9b5e
import com.en.sphzb.entity.mapper.CaseMapper;
import com.en.sphzb.repository.CaseRepository;
import com.en.sphzb.repository.CaseTypeRepository;
import com.en.sphzb.repository.QuestionsRepository;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 案件装换为题目
 * create by en
 * at 2019/2/25 14:16
 **/
@Service
public class CaseToQuestionService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

//    @Scheduled(cron = "0 0 0/5 * * ?")
    public void parseQuestion() {
        // 1. 查询所有案别类型，加载至内存
        List<CaseType> allCaseType = new CopyOnWriteArrayList<>(caseTypeRepository.findAll());
        // 2. 查询未转化成题目的案件数量，如果超过1000个，则进行分批处理
        Set<Long> caseToParseSet = caseMapper.findCaseToParse();
        // 3. 对案件案情进行分词，模糊时间、地点、人物
        for (long caseId : caseToParseSet) {
            Optional<Case> caseOptional = caseRepository.findById(caseId);
            if (!caseOptional.isPresent()) {
                continue;
            }
            Case aCase = caseOptional.get();
            String description = aCase.getCaseDescription();
            // todo 模糊时间

            // 4. 根据分词内容，查询此案件可能所属的类别, 保存
            String[] words = this.seg(description, SegmentationAlgorithm.FullSegmentation).split(" ");
            Map<String, Integer> caseTypeMap = new ConcurrentHashMap<>();
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                for (int i = 0; i < allCaseType.size(); i++) {
                    String typeName = allCaseType.get(i).getTypeName();
                    if (typeName.contains(word)) {
                        if (caseTypeMap.containsKey(word)) {
                            caseTypeMap.put(typeName, caseTypeMap.get(word) + 1);
                        } else {
                            caseTypeMap.put(typeName, 1);
                        }
                    }
                }
            }
            List<CaseTypeFrequency> caseTypeFrequencyList = new ArrayList<>();
            for (String key : caseTypeMap.keySet()) {
                caseTypeFrequencyList.add(new CaseTypeFrequency(key, caseTypeMap.get(key)));
            }
            if (caseTypeFrequencyList.isEmpty()) {
                continue;
            }
            caseTypeFrequencyList.sort(new CaseTypeFrequency());
            Question question = new Question();
            question.setCaseId(caseId);
            question.setStatus(1);
            // todo
            question.setQuestionContent(description);
            question.setAnswerA(caseTypeFrequencyList.get(0).caseType);
            question.setAnswerB(caseTypeFrequencyList.get(1).caseType);
            question.setAnswerC(caseTypeFrequencyList.get(2).caseType);
            question.setAnswerD(caseTypeFrequencyList.get(3).caseType);
            questionsRepository.save(question);
        }
    }

    /**
     * 获取文本的所有分词结果
     * @param text 文本
     * @return 所有的分词结果，KEY 为分词器模式，VALUE 为分词器结果
     */
    private Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();
        for(SegmentationAlgorithm segmentationAlgorithm : SegmentationAlgorithm.values()){
            map.put(segmentationAlgorithm.getDes(), seg(text, segmentationAlgorithm));
        }
        return map;
    }

    /**
     * 获取文本的所有分词结果
     * @param text 文本
     * @return 所有的分词结果，去除重复
     */
    private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
        StringBuilder result = new StringBuilder();
        for(Word word : WordSegmenter.segWithStopWords(text, segmentationAlgorithm)){
            result.append(word.getText()).append(" ");
        }
        return result.toString();
    }

    /**
     * 案别类型及频次
     */
    class CaseTypeFrequency implements Comparable,Comparator {

        public CaseTypeFrequency() {
        }

        public CaseTypeFrequency(String caseType, Integer frequency) {
            this.caseType = caseType;
            this.frequency = frequency;
        }

        private String caseType;

        private Integer frequency;


        @Override
        public int compareTo(Object o) {
            if (o instanceof CaseTypeFrequency)
                return frequency.compareTo(((CaseTypeFrequency) o).frequency);
            else
                throw new RuntimeException();
        }

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof CaseTypeFrequency && o2 instanceof CaseTypeFrequency)
                return ((CaseTypeFrequency) o1).compareTo((o2));
            else
                throw new RuntimeException();
        }
    }

}
