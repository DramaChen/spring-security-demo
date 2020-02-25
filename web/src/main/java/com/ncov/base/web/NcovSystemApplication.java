package com.ncov.base.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ncov.base")
@MapperScan(basePackages = "com.ncov.base.web.dao")
public class NcovSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NcovSystemApplication.class,args);
    }
}
