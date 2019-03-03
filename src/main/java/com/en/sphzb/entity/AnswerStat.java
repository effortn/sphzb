package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 答题结果统计表
 * create by en
 * at 2019/2/21 10:13
 **/
@Data
@Entity
public class AnswerStat {

    /**
     * 统计主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 总答题次数
     */
    private Long totalAnswer = 0l;

    /**
     * 选择A的次数
     */
    @Column(name = "choice_a")
    private Long choiceA = 0l;

    /**
     * 选择B的次数
     */
    @Column(name = "choice_b")
    private Long choiceB = 0l;

    /**
     * 选择C的次数
     */
    @Column(name = "choice_c")
    private Long choiceC = 0l;

    /**
     * 选择D的次数
     */
    @Column(name = "choice_d")
    private Long choiceD = 0l;

    /**
     * 备注
     */
    private String remark;

}
