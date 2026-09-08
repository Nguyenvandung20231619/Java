package vn.edu.eaut.lab15;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.eaut.lab15.entity.UserEntity;
import vn.edu.eaut.lab15.repository.UserRepository;

@SpringBootApplication
public class Lab15Application {
    public static void main(String[] args) { SpringApplication.run(Lab15Application.class, args); }
    @Bean CommandLineRunner initData(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) repo.save(new UserEntity("admin", encoder.encode("123456"), "ROLE_ADMIN"));
            if (repo.findByUsername("user").isEmpty()) repo.save(new UserEntity("user", encoder.encode("123456"), "ROLE_USER"));
        };
    }
}
