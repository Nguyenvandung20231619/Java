package vn.edu.eaut.lab14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.eaut.lab14.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
