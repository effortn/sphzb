package com.en.sphzb.service;

import com.en.sphzb.SphzbApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CaseToQuestionServiceTest extends SphzbApplicationTests {

    @Autowired
    private CaseToQuestionService caseToQuestionService;

    @Test
    public void parseQuestion() {
        caseToQuestionService.parseQuestion();
    }
}