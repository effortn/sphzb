package com.en.sphzb.repository;

import com.en.sphzb.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * create by en
 * at 2019/2/21 10:39
 **/
@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
}
