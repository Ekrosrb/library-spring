package com.ekros.libraryspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = {UserRepo.class, BookRepo.class, OrderRepo.class})
public class LibraryspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryspringApplication.class, args);
    }

}
