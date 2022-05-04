package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlThreadApplication.class, args);

        System.out.println("hello test1");
    }

}
