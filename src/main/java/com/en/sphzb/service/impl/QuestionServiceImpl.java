package com.en.sphzb.service.impl;

import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.Question;
import com.en.sphzb.repository.AnswerRecordRepository;
import com.en.sphzb.repository.AnswerStatRepository;
import com.en.sphzb.repository.QuestionsRepository;
import com.en.sphzb.service.QuestionService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private AnswerRecordRepository answerRecordRepository;

    @Autowired
    private AnswerStatRepository answerStatRepository;

    @Override
    public Question getQuestion() {
        List<Question> questions = questionsRepository.findByStatus(1);
        int v = (int) (Math.random() * questions.size());
        return questions.get(v);
    }

    @Override
    public AnswerRecord saveAnswer(String ip, String user, Long questionId, String answer) {
        AnswerRecord answerRecord = new AnswerRecord();
        //答案ID，格式：YYYYMMDDHH24MISS-IP-QUESTION_ID
        DateTime dateTime = new DateTime();
        String answerId = dateTime.toString("yyyyMMddHHmmss").concat("-").concat(ip).concat("-").concat(questionId.toString());
        answerRecord.setAnswerId(answerId);
        answerRecord.setIp(ip);
        answerRecord.setAnswer(answer);
        answerRecord.setQuestionId(questionId);
        answerRecord.setUserCode(user);
        return answerRecordRepository.save(answerRecord);
    }

    @Override
    public Page<AnswerStat> getStat(PageRequest page) {
        Page<AnswerStat> all = answerStatRepository.findAll(page);
        return all;
    }

}