package com.repositories;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.repositories.dao")
public class RepositoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepositoriesApplication.class, args);
    }

}
