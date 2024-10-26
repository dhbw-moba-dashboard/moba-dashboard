package de.dhbwkarlsruhe.modellbahn.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModellbahnApplication {

    public static void main(String[] args) {
        System.out.println("Hello World");
        SpringApplication.run(ModellbahnApplication.class, args);
    }

}
