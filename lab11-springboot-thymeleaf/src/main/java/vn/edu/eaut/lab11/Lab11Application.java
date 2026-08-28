package vn.edu.eaut.lab11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "vn.edu.eaut.lab11")
@EnableJpaRepositories(basePackages = "vn.edu.eaut.lab11.repository")
@EntityScan(basePackages = "vn.edu.eaut.lab11.model")
public class Lab11Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab11Application.class, args);
    }
}