package com.rajesh.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InsurancePortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsurancePortalApplication.class, args);
    }
}
