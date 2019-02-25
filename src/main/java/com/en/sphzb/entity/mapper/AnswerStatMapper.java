package com.en.sphzb.entity.mapper;

import com.en.sphzb.VO.AnswerStatVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnswerStatMapper {

    List<AnswerStatVO> selectStatByPage(@Param("begin") int begin, @Param("end") int end);

    @Select("select count(*) from answer_stat")
    int count();

}
