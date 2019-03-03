package com.en.sphzb.repository;

import com.en.sphzb.entity.Samples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 样例表持久层
 * create by en
 * at 2019/3/2 13:02
 **/
@Repository
public interface SamplesRepository extends JpaRepository<Samples, Integer> {

    /**
     * 查询所有的案由名称
     * @return
     */
    @Query(value = "SELECT DISTINCT aymc FROM Samples")
    List<String> findAymc();

    /**
     * 根据案由名称查询案件
     * @param aymc  案由名称
     * @return
     */
    List<Samples> findAllByAymc(String aymc);

}
