package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    private Long statId;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 总答题次数
     */
    private Integer totalAnswer;

    /**
     * 选择A的次数
     */
    @Column(name = "choice_a")
    private Integer choiceA;

    /**
     * 选择B的次数
     */
    @Column(name = "choice_b")
    private Integer choiceB;

    /**
     * 选择C的次数
     */
    @Column(name = "choice_c")
    private Integer choiceC;

    /**
     * 选择D的次数
     */
    @Column(name = "choice_d")
    private Integer choiceD;

    /**
     * 备注
     */
    private String remark;

}
