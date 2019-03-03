package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 样例实体类
 * create by en
 * at 2019/3/2 12:57
 **/
@Entity
@Data
public class Samples {

    /**
     * 主键
     **/
    @Id
    private Integer sampleId;
    
    /**
     * 案件编号
     **/
    private String ajbh;

    /**
     * 简要案情
     **/
    private String jyaq;

    /**
     * 案由名称
     **/
    private String aymc;

}
