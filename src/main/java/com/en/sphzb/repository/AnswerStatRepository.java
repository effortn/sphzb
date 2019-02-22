package com.en.sphzb.repository;

import com.en.sphzb.entity.AnswerStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 答案统计表持久层
 * create by en
 * at 2019/2/21 10:36
 **/
@Repository
public interface AnswerStatRepository extends JpaRepository<AnswerStat, Long> {
}
