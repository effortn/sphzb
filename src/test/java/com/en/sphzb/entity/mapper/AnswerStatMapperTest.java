package com.en.sphzb.entity.mapper;

import com.en.sphzb.SphzbApplicationTests;
import com.en.sphzb.VO.AnswerStatVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AnswerStatMapperTest extends SphzbApplicationTests {

    @Autowired
    private AnswerStatMapper mapper;

    @Test
    public void selectStatByPage() {
        List<AnswerStatVO> answerStatVOS = mapper.selectStatByPage(0, 10);
        Assert.assertTrue(answerStatVOS.size() == 0);
    }

    @Test
    public void count() {
        int count = mapper.count();
        Assert.assertTrue(count == 0);
    }

}