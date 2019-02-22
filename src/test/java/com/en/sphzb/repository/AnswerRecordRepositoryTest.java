package com.en.sphzb.repository;

import com.en.sphzb.SphzbApplicationTests;
import com.en.sphzb.entity.AnswerRecord;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnswerRecordRepositoryTest extends SphzbApplicationTests {

    @Autowired
    private AnswerRecordRepository answerRecordRepository;

    @Test
    public void queryAll() {
        List<AnswerRecord> all = answerRecordRepository.findAll();
        Assert.assertTrue(all.size() == 0);
    }
}