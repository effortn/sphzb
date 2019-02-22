package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 题目实体类
 * create by en
 * at 2019/2/21 9:34
 **/
@Entity
@Data
public class Question {

    /**
     * 题目主键
     */
    @Id
    private Long questionId;

    /**
     * 案件主键
     */
    private Long caseId;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 答案A
     */
    private Integer answerA;

    /**
     * 答案B
     */
    private Integer answerB;

    /**
     * 答案C
     */
    private Integer answerC;

    /**
     * 答案D
     */
    private Integer answerD;

    /**
     * 题目状态，0：失效，1：有效，2：已存在正确答案
     */
    private int status;

    /**
     * 题目创建时间
     */
    private Date createTime;

}
