package vn.edu.eaut.lab15.service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import vn.edu.eaut.lab15.entity.UserEntity;
import vn.edu.eaut.lab15.repository.UserRepository;
import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;
    public CustomUserDetailsService(UserRepository repository) { this.repository=repository; }
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u=repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản"));
        return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(),List.of(new SimpleGrantedAuthority(u.getRole())));
    }
}
