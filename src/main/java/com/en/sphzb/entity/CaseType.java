package com.en.sphzb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 案件类别表
 * create by en
 * at 2019/2/21 9:38
 **/
@Data
@Entity
public class CaseType {
    
    /**
     * 类别主键
     **/
    @Id
    private Integer typeId;
    
    /**
     * 类别名称
     **/
    private String typeName;

}
