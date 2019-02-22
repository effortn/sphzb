package com.en.sphzb.repository;

import com.en.sphzb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目表持久层
 * create by en
 * at 2019/2/21 11:31
 **/
@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {

    List<Question> findByStatus(int status);

}
