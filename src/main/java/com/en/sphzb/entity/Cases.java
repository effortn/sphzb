package com.en.sphzb.entity;

import lombok.Data;

<<<<<<< HEAD:src/main/java/com/en/sphzb/entity/Case.java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
=======
import javax.persistence.*;
>>>>>>> 2991ad825f826d5057bab562fe48c2ce836f9b5e:src/main/java/com/en/sphzb/entity/Cases.java
import java.util.Date;

/**
 * 案件表
 * create by en
 * at 2019/2/21 9:29
 **/
@Data
@Entity
<<<<<<< HEAD:src/main/java/com/en/sphzb/entity/Case.java
@Table(name = "CASES")
public class Case {
=======
public class Cases {
>>>>>>> 2991ad825f826d5057bab562fe48c2ce836f9b5e:src/main/java/com/en/sphzb/entity/Cases.java

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
