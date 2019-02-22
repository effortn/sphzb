package com.en.sphzb.service;

import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.Question;

/**
 *
 * create by en
 * at 2019/2/22 9:50
 **/
public interface QuestionService {

    /**
     * 获取题目，随机获取
     * @return
     */
    Question getQuestion();

    AnswerRecord saveAnswer(String ip, String user, Long questionId, String answer);

}
