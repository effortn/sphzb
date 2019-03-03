package com.en.sphzb.service;

import com.en.sphzb.SphzbApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class KeyWeightServiceTest extends SphzbApplicationTests {

    @Autowired
    private KeyWeightService keyWeightService;

    @Test
    public void calculateWeight() {
        keyWeightService.calculateWeight();
    }
}