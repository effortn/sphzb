package com.en.sphzb.repository;

import com.en.sphzb.entity.CaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 案件类别表持久层
 * create by en
 * at 2019/2/21 11:07
 **/
@Repository
public interface CaseTypeRepository extends JpaRepository<CaseType, Integer> {


}
