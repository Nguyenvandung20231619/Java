package vn.edu.eaut.lab12.service;

import org.springframework.stereotype.Service;
import vn.edu.eaut.lab12.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public StudentService() {
        // Dữ liệu mẫu khởi tạo
        save(new Student(null, "20231619", "Nguyễn Văn Dũng", "dung@eaut.edu.vn", "DCCNTT13.10.1"));
        save(new Student(null, "SV00002", "Trần Thị Bình", "binh@eaut.edu.vn", "DCCNTT13.10.2"));
    }

    // Bài 2: Lấy tất cả
    public List<Student> findAll() {
        return students;
    }

    // Bài 6: Tìm theo ID
    public Optional<Student> findById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    // Bài 2 & Bài 7: Thêm mới hoặc Cập nhật
    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
            students.add(student);
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(student.getId())) {
                    students.set(i, student);
                    break;
                }
            }
        }
    }

    // Bài 8: Xóa sinh viên
    public void deleteById(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    // Bài 9: Tìm kiếm theo họ tên
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return students;
        }
        return students.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    // Bài 10: Validation - Kiểm tra mã sinh viên bị trùng
    public boolean existsByStudentCode(String studentCode, Long currentId) {
        return students.stream()
                .anyMatch(s -> s.getStudentCode().equalsIgnoreCase(studentCode) 
                        && (currentId == null || !s.getId().equals(currentId)));
    }
}