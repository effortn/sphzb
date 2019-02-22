package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 答题记录表
 * create by en
 * at 2019/2/21 10:01
 **/
@Entity
@Data
public class AnswerRecord {

    /**
     * 答题记录主键,格式为：YYYYMMDDHH24MISS-IP-QUESTION_ID
     */
    @Id
    private String answerId;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 题目主键
     */
    private Long questionId;

    /**
     * 答案
     */
    private String answer;

}
