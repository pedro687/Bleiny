package com.spiet.bleiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BleinyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BleinyApplication.class, args);
    }

    

}
