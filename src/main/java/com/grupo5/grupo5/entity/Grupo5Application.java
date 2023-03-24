package com.grupo5.grupo5.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Grupo5Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupo5Application.class, args);
    }

}
