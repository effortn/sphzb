package com.en.sphzb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.en.sphzb.entity.mapper")
public class SphzbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SphzbApplication.class, args);
    }

}
