package com.yiran.syt.hosp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Ale on 2021/4/15
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yiran.syt")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
