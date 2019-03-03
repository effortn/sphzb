package com.en.sphzb.repository;

import com.en.sphzb.SphzbApplicationTests;
import com.en.sphzb.entity.Samples;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SamplesRepositoryTest extends SphzbApplicationTests {

    @Autowired
    private SamplesRepository samplesRepository;


    @Test
    public void findAymcTest() {
        List<String> aymcSet = samplesRepository.findAymc();
        List<Samples> allByAymc = samplesRepository.findAllByAymc((aymcSet).get(1));
        Assert.assertNotNull(allByAymc);
    }

}