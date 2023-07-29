package com.repositories;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.awt.*;

@SpringBootTest
class RepositoriesApplicationTests {
    @Autowired
    DataSource dataSource;

    @SneakyThrows
    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

}
