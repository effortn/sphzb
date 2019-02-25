package com.en.sphzb.entity.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface CaseMapper {

    @Select("select c.case_id from `case` c left join question q on c.case_id = q.case_id " +
            "where q.question_id IS NULL AND c.status = 1 ORDER BY case_id")
    Set<Long> findCaseToParse();

}
