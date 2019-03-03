package com.en.sphzb.service;

import com.en.sphzb.entity.CaseType;
import com.en.sphzb.entity.KeyWeight;
import com.en.sphzb.entity.Samples;
import com.en.sphzb.repository.CaseTypeRepository;
import com.en.sphzb.repository.KeyWeightRepository;
import com.en.sphzb.repository.SamplesRepository;
import com.en.sphzb.utils.CasesUtil;
import com.en.sphzb.utils.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 关键词权重
 * create by en
 * at 2019/3/1 15:43
 **/
@Service
@Slf4j
public class KeyWeightService {

    // 权重阈值
    private static int WEIGHT_VALUE = 100;

    // 最小权重值
    private static int MIN_WEIGHT = 5;

    @Autowired
    private SamplesRepository samplesRepository;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private KeyWeightRepository keyWeightRepository;

    /**
     * 根据样例数据计算权重
     */
    @Transactional
    public void calculateWeight() {
        log.info("【权重计算】开始！");
        // 清空权重表和案件类型表
        caseTypeRepository.deleteAll();
        keyWeightRepository.deleteAll();
        // 1. 查出所有案由名称，按照案由名称一个一个的去计算
        List<String> aymcList = samplesRepository.findAymc();
        log.debug("【权重计算】案由名称总量：{}。", aymcList.size());
        // 2. 遍历案由名称列表，查询每个案由名称下的所有案件
        for (int i = 0; i < aymcList.size(); i++) {
            String aymc = aymcList.get(i);
            List<Samples> samples = samplesRepository.findAllByAymc(aymc);
            // 3. 对案件遍历，对案情中的"时间信息，报警人，手机号，数字"的进行替换，分词，对词的出现频率进行统计，
            //      词语权重计算公式：权重 = 频率 * 权重阈值
            int total = samples.size();
            // 分词结果，每个案情的分词保存为一个Set
            List<Set<String>> wordsList = new CopyOnWriteArrayList<>();
            for (int j = 0; j < total; j++) {
                Samples samp = samples.get(j);
                String jyaq = samp.getJyaq();
                // 将案情无用字符串清除
                String aq = CasesUtil.deletePattern(jyaq);
                // 将案情分词
                String[] words = WordUtil.seg(aq, SegmentationAlgorithm.FullSegmentation).split(" ");
                // 防止重复，放入Set集合中
                Set<String> wordSet = new HashSet<>(Arrays.asList(words));
                wordsList.add(wordSet);
            }
            // 4. 计算出最终词语出现次数统计结果，key：词语，value：出现次数
            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
            wordsList.stream().forEach(
                words -> {
                    words.stream().forEach( word -> {
                        if (map.containsKey(word)) {
                            int count = map.get(word) + 1;
                            map.put(word, count);
                        } else {
                            map.put(word, 1);
                        }
                    });
                }
            );
            // 5. 保存案件类型，获取案件类型主键值
            CaseType caseType = new CaseType();
            caseType.setTypeName(aymc);
            caseType = caseTypeRepository.save(caseType);
            // 6. 遍历map，构建权重实体类，保存在数据库
            List<KeyWeight> toSave = new ArrayList<>();
            for (String key : map.keySet()) {
                KeyWeight keyWeight = new KeyWeight();
                //      词语权重计算公式：权重 = 频率 * 权重阈值
                Double weight = (double)map.get(key) / (double) samples.size() * WEIGHT_VALUE;
                // 如果权重小于最小权重值的话，不计
                if (weight < MIN_WEIGHT) {
                    continue;
                }
                keyWeight.setTypeId(caseType.getTypeId());
                keyWeight.setKeyContent(key);
                keyWeight.setWeight(weight);
                keyWeight.setStatus(1);
                toSave.add(keyWeight);
            }
            keyWeightRepository.saveAll(toSave);
        }


    }


}
