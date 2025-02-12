package com.example.idea_lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.idea_lab.mapper")
@SpringBootApplication
public class IdeaLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaLabApplication.class, args);
    }

}
