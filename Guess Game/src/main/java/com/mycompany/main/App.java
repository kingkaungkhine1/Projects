package com.mycompany.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mycompany.controller")
@ComponentScan("com.mycompany.service")
@ComponentScan("com.mycompany.dao")

public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}