package com.en.sphzb.service;

import com.en.sphzb.SphzbApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AnswerStatServiceTest extends SphzbApplicationTests {

    @Autowired
    private AnswerStatService answerStatService;

    @Test
    public void stat() {
        answerStatService.stat();
    }
}