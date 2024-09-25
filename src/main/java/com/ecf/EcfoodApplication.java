package com.ecf;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("com.ecf.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class EcfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcfoodApplication.class, args);
    }

}
