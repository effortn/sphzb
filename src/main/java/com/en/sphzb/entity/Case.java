package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 案件表
 * create by en
 * at 2019/2/21 9:29
 **/
@Data
@Entity
public class Case {

    /**
     * 案件主键
     */
    @Id
    private Long caseId;

    /**
     * 案件编号
     */
    private String caseCode;

    /**
     * 案件案情描述
     */
    private String caseDescription;

    /**
     * 创建时间
     */
    private Date createTime;


}
