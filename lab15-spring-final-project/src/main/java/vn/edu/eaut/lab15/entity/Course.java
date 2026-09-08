package vn.edu.eaut.lab15.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = "course_code"))
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã môn học không được để trống")
    @Column(name = "course_code", nullable = false, unique = true, length = 30)
    private String courseCode;

    @NotBlank(message = "Tên môn học không được để trống")
    @Column(name = "course_name", nullable = false, length = 150)
    private String courseName;

    @Min(value = 1, message = "Số tín chỉ phải lớn hơn 0")
    @Column(nullable = false)
    private Integer credits;

    public Course() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
}
