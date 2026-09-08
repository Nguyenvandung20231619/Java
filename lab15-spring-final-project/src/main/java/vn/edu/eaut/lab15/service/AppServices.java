package vn.edu.eaut.lab15.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.eaut.lab15.entity.*;
import vn.edu.eaut.lab15.repository.StudentRepository;
import vn.edu.eaut.lab15.repository.CourseRepository;
import vn.edu.eaut.lab15.repository.EnrollmentRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppServices {
    private final StudentRepository studentRepository; private final CourseRepository courseRepository; private final EnrollmentRepository enrollmentRepository;
    public AppServices(StudentRepository students, CourseRepository courses, EnrollmentRepository enrollments) { studentRepository=students; courseRepository=courses; enrollmentRepository=enrollments; }
    public long countStudents() { return studentRepository.count(); }
    public long countCourses() { return courseRepository.count(); }
    public long countEnrollments() { return enrollmentRepository.count(); }
    public List<Enrollment> getAllEnrollments() { return enrollmentRepository.findAll(); }
    @Transactional public void enroll(Long studentId, Long courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) throw new IllegalArgumentException("Sinh viên đã đăng ký môn học này");
        Enrollment e = new Enrollment();
        e.setStudent(studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên")));
        e.setCourse(courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy môn học")));
        e.setEnrollDate(LocalDate.now()); enrollmentRepository.save(e);
    }
    public void deleteEnrollment(Long id) { enrollmentRepository.deleteById(id); }
}
