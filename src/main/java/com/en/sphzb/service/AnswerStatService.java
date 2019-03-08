package com.en.sphzb.service;

import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.Question;
import com.en.sphzb.entity.mapper.AnswerRecordMapper;
import com.en.sphzb.entity.mapper.AnswerStatMapper;
import com.en.sphzb.repository.AnswerRecordRepository;
import com.en.sphzb.repository.AnswerStatRepository;
import com.en.sphzb.repository.CaseRepository;
import com.en.sphzb.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Autowired
    private QuestionsRepository questionsRepository;

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
                statResult.put(questionId, answerStat);
            }
            Long c = (Long) answerStatMap.get("c");
            answerStat.setTotalAnswer(answerStat.getTotalAnswer() + c);
            if (answerStatMap.get("answer") == null || answerStatMap.get("answer").equals("")) {
                continue;
            }
            switch (answerStatMap.get("answer").toString().charAt(0)) {
                case 'A' :
                    answerStat.setChoiceA(c);
                    break;
                case 'B' :
                    answerStat.setChoiceB(c);
                    break;
                case 'C' :
                    answerStat.setChoiceC(c);
                    break;
                case 'D' :
                    answerStat.setChoiceD(c);
                    break;
            }
        }
        List<AnswerStat> result = new ArrayList<>(statResult.values());
        result.forEach( x -> {
            // 1. 若超过十次未提交答案，视为作废体
            if (x.getTotalAnswer() - x.getChoiceA() - x.getChoiceB() - x.getChoiceC() - x.getChoiceD() > 10) {
                x.setRemark("该题超过十次未提交答案，视为作废!");
                Optional<Question> question = questionsRepository.findById(x.getQuestionId());
                if (question.isPresent()) {
                    question.get().setStatus(0);
                    questionsRepository.save(question.get());
                }
                return;
            }
            // 2. 判断是否已有正确答案，答案的答题次数占比是否超过 80%
            long max = x.getChoiceA();
//            String choice = "最多选项没有超过80%占比，尚没有正确答案";
            String choice = "A";
            if (max < x.getChoiceB()) {
                choice = "B";
                max = x.getChoiceB();
            }
            if (max < x.getChoiceC()) {
                choice = "C";
                max = x.getChoiceC();
            }
            if (max < x.getChoiceD()) {
                choice = "D";
                max = x.getChoiceD();
            }
            if (((double)max / (double)x.getTotalAnswer()) >= 0.8) {
                x.setRemark(choice + "超过总答题次数80%占比，为正确答案");
            } else {
                x.setRemark("最多选项没有超过80%占比，尚没有正确答案");
            }
        });
        answerStatRepository.deleteAll();
        answerStatRepository.saveAll(result);
    }

}
