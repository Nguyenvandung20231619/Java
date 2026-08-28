package vn.edu.eaut.lab11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.eaut.lab11.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}