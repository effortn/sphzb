package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 关键词权重表
 * create by en
 * at 2019/2/21 9:40
 **/
@Data
@Entity
public class KeyWeight {

    /**
     * 关键词ID
     */
    @Id
    private Long keyId;

    /**
     * 关键词
     */
    private String keyContent;

    /**
     * 类型ID
     */
    private Long typeId;

    /**
     * 权重值
     */
    private String weight;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态，1：有效，0：无效
     */
    private int status;

}
