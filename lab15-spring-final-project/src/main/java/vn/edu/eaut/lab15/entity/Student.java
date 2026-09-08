package vn.edu.eaut.lab15.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "student_code"))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Column(name = "student_code", nullable = false, unique = true, length = 30)
    private String studentCode;

    @NotBlank(message = "Họ tên không được để trống")
    @Column(nullable = false, length = 120)
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Column(nullable = false, length = 150)
    private String email;

    public Student() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
