package vn.edu.eaut.lab14;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.eaut.lab14.entity.UserEntity;
import vn.edu.eaut.lab14.repository.UserRepository;

@SpringBootApplication
public class Lab14Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab14Application.class, args);
    }

    // Tự động khởi tạo tài khoản vào CSDL H2 khi chạy app
    @Bean
    CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new UserEntity("admin", passwordEncoder.encode("123456"), "ROLE_ADMIN"));
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new UserEntity("user", passwordEncoder.encode("123456"), "ROLE_USER"));
            }
        };
    }
}