package vn.edu.eaut.lab6.store;

import vn.edu.eaut.lab6.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentStore {
    private static final List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("SV001", "Nguyen Van An", "DCCNTT12", "an@example.com"));
        students.add(new Student("SV002", "Tran Thi Binh", "DCCNTT12", "binh@example.com"));
        students.add(new Student("SV003", "Le Van Cuong", "DCCNTT13", "cuong@example.com"));
    }

    public static List<Student> findAll() {
        return students;
    }

    public static void add(Student student) {
        students.add(student);
    }

    // Bài 6: Tìm kiếm sinh viên theo tên (Không phân biệt hoa/thường)
    public static List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        String lower = keyword.trim().toLowerCase();
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    // Bài 7: Xóa sinh viên khỏi danh sách
    public static boolean delete(String id) {
        return students.removeIf(s -> s.getId().equalsIgnoreCase(id));
    }

    // Bài 8: Tìm theo ID để hiển thị lên form sửa
    public static Student findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // Bài 8: Cập nhật thông tin sinh viên
    public static void update(Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(updated.getId())) {
                students.set(i, updated);
                break;
            }
        }
    }
}