package vn.edu.eaut.lab11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.eaut.lab11.model.Student;
import vn.edu.eaut.lab11.repository.StudentRepository;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Lấy danh sách sinh viên
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    // 2. Hiển thị form thêm mới sinh viên
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    // 3. Lưu sinh viên (Thêm mới hoặc Cập nhật)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // 4. Hiển thị form chỉnh sửa (Đã fix kiểm tra Null)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            model.addAttribute("isEdit", true);
            return "student-form";
        }
        return "redirect:/students"; // Nếu không thấy ID thì quay về danh sách
    }

    // 5. Xóa sinh viên theo ID
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}