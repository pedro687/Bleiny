package com.bleiny.communities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@SpringBootApplication
public class CommunitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunitiesApplication.class, args);
    }

}
