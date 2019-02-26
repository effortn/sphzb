package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 案件表
 * create by en
 * at 2019/2/21 9:29
 **/
@Data
@Entity
public class Cases {

    /**
     * 案件主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
