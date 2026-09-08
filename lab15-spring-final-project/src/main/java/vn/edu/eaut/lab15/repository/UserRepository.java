package vn.edu.eaut.lab15.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.eaut.lab15.entity.UserEntity;
import java.util.Optional;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
