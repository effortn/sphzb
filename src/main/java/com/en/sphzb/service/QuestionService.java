package com.en.sphzb.service;

import com.en.sphzb.VO.AnswerStatVO;
import com.en.sphzb.entity.AnswerRecord;
import com.en.sphzb.entity.AnswerStat;
import com.en.sphzb.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 题目相关Service
 * create by en
 * at 2019/2/22 9:50
 **/
public interface QuestionService {

    /**
     * 获取题目，随机获取
     * @return
     */
    Question getQuestion();

    /**
     * 保存答题记录
     * @param ip            答题人IP地址
     * @param user          用户编号
     * @param questionId    题目ID
     * @param answer        答案
     * @return       AnswerRecord   保存好的答题记录实体
     */
    AnswerRecord saveAnswer(String ip, String user, Long questionId, String answer);


    Page<AnswerStatVO> getStat(int page, int size);

}
