package com.en.sphzb.repository;

import com.en.sphzb.entity.AnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 答题记录表持久层
 * create by en
 * at 2019/2/21 10:32
 **/
@Repository
public interface AnswerRecordRepository extends JpaRepository<AnswerRecord, String> {


}
