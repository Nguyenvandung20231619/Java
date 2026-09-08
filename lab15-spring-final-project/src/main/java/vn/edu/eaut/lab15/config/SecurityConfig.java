package vn.edu.eaut.lab15.config;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login","/css/**","/error").permitAll()
            .requestMatchers("/students/create","/students/edit/**","/students/save","/students/delete/**").hasRole("ADMIN")
            .requestMatchers("/courses/create","/courses/edit/**","/courses/save","/courses/delete/**").hasRole("ADMIN")
            .requestMatchers("/enrollments/create","/enrollments/save","/enrollments/delete/**").hasRole("ADMIN")
            .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
            .exceptionHandling(exception -> exception.accessDeniedPage("/403"));
        return http.build();
    }
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
