package com.en.sphzb.repository;

import com.en.sphzb.entity.KeyWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关键字权重表持久层
 * create by en
 * at 2019/2/21 11:09
 **/
@Repository
public interface KeyWeightRepository extends JpaRepository<KeyWeight, Long> {

    List<KeyWeight> findAllByKeyContent(String key);
}
