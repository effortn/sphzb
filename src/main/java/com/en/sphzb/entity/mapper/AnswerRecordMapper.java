package com.en.sphzb.entity.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AnswerRecordMapper {

    @Select("select count(*) c,question_id,answer from answer_record group by question_id,answer")
    List<Map<String, Object>> answerStat();

}
