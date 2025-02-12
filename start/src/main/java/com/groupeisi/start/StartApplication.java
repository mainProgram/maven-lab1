package com.groupeisi.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.groupeisi")
@ComponentScan(basePackages = { "com.groupeisi.services", "com.groupeisi.web" })
@EnableJpaRepositories(basePackages = "com.groupeisi.services.dao")
@EntityScan(basePackages = "com.groupeisi.services.entities")
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}