package com.ecf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ecf.mapper")
@SpringBootApplication
public class EcfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcfoodApplication.class, args);
    }

}
