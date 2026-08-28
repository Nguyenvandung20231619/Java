package vn.edu.eaut.lab12.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.eaut.lab12.model.Student;
import vn.edu.eaut.lab12.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Bài 3 & Bài 9: Xem danh sách + Tìm kiếm theo tên
    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("students", studentService.searchByName(keyword));
        model.addAttribute("keyword", keyword);
        return "students/list";
    }

    // Bài 4: Form thêm sinh viên
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "students/form";
    }

    // Bài 5 & Bài 10: Validation & Lưu dữ liệu (Check trùng mã SV)
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student,
                       BindingResult result, Model model) {
        // Kiểm tra trùng mã SV (Bài 10)
        if (studentService.existsByStudentCode(student.getStudentCode(), student.getId())) {
            result.rejectValue("studentCode", "error.student", "Mã sinh viên đã tồn tại trong hệ thống");
        }

        if (result.hasErrors()) {
            model.addAttribute("isEdit", student.getId() != null);
            return "students/form";
        }

        studentService.save(student);
        return "redirect:/students";
    }

    // Bài 6: Xem chi tiết sinh viên
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOpt = studentService.findById(id);
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            return "students/detail";
        }
        return "redirect:/students";
    }

    // Bài 7: Sửa thông tin sinh viên
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOpt = studentService.findById(id);
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            model.addAttribute("isEdit", true);
            return "students/form";
        }
        return "redirect:/students";
    }

    // Bài 8: Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}