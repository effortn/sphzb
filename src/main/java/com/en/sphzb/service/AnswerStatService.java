package com.en.sphzb.service;

import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.mapper.AnswerRecordMapper;
import com.en.sphzb.entity.mapper.AnswerStatMapper;
import com.en.sphzb.repository.AnswerRecordRepository;
import com.en.sphzb.repository.AnswerStatRepository;
import com.en.sphzb.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 答案统计服务
 * create by en
 * at 2019/2/28 16:19
 **/
@Service
public class AnswerStatService {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @Autowired
    private AnswerStatRepository answerStatRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void stat() {
        List<Map<String, Object>> answerStatList = answerRecordMapper.answerStat();
        Map<String, AnswerStat> statResult = new HashMap<>();
        for (int i = 0; i < answerStatList.size(); i++) {
            Map<String, Object> answerStatMap = answerStatList.get(i);
            String questionId = String.valueOf(answerStatMap.get("question_id"));
            AnswerStat answerStat;
            if (statResult.containsKey(questionId)) {
                answerStat = statResult.get(questionId);
            } else {
                answerStat = new AnswerStat();
                answerStat.setQuestionId(Long.parseLong(questionId));
                answerStat.setStatId((long) i);
                statResult.put(questionId, answerStat);
            }
            if (answerStatMap.get("answer") == null || answerStatMap.get("answer").equals("")) {
                answerStat.setTotalAnswer((Integer)answerStatMap.get("c"));
                continue;
            }
            switch ((char)answerStatMap.get("answer")) {
                case 'A' :
                    answerStat.setChoiceA((Integer)answerStatMap.get("c"));
                    break;
                case 'B' :
                    answerStat.setChoiceB((Integer)answerStatMap.get("c"));
                    break;
                case 'C' :
                    answerStat.setChoiceC((Integer)answerStatMap.get("c"));
                    break;
                case 'D' :
                    answerStat.setChoiceD((Integer)answerStatMap.get("c"));
                    break;
            }
        }
        answerStatRepository.deleteAll();
        answerStatRepository.saveAll(new ArrayList<>(statResult.values()));
    }

}
