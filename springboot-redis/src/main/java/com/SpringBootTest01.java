package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class SpringBootTest01 {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTest01.class);
    }
}
