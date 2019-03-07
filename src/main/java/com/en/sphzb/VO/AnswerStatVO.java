package com.en.sphzb.VO;

import lombok.Data;

/**
 * 答题统计VO
 * create by en
 * at 2019/2/25 9:53
 **/
@Data
public class AnswerStatVO {

    /**
     * 统计主键
     */
    private Long statId;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 题目内容
     **/
    private String questionContent;
    /**
     * 原题内容
     **/
    private String casesDescription;
    /**
     * 总答题次数
     */
    private Integer totalAnswer;

    /**
     * 答案A
     **/
    private String answerA;

    /**
     * 选择A的次数
     */
    private Integer choiceA;

    /**
     * 答案B
     **/
    private String answerB;

    /**
     * 选择B的次数
     */
    private Integer choiceB;

    /**
     * 答案C
     **/
    private String answerC;

    /**
     * 选择C的次数
     */
    private Integer choiceC;

    /**
     * 答案D
     **/
    private String answerD;

    /**
     * 选择D的次数
     */
    private Integer choiceD;

    /**
     * 备注
     */
    private String remark;

}
