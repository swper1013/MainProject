package com.example.exnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class ExnoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExnoteApplication.class, args);
    }

}
