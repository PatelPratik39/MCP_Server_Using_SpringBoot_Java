package com.fitness.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessMicroserviceApplication.class, args);
        System.err.println("User Service is running... 🏌🏻‍♂️");
    }

}
