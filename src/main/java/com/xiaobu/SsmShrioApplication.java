package com.xiaobu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.xiaobu.mapper")
public class SsmShrioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SsmShrioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("服务启动成功.....");
    }
}

