package com.ekros.libraryspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
public class LibraryspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryspringApplication.class, args);
    }

}
