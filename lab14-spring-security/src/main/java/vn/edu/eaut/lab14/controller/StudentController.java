package vn.edu.eaut.lab14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.eaut.lab14.entity.StudentEntity;
import vn.edu.eaut.lab14.repository.StudentRepository;

import java.util.Objects;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students/list";
    }

    @GetMapping("/students/create")
    public String createStudent(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "students/form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute StudentEntity student) {
        studentRepository.save(Objects.requireNonNull(student));
        return "redirect:/students";
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(Objects.requireNonNull(id));
        return "redirect:/students";
    }

    @GetMapping("/courses")
    public String listCourses() {
        return "courses/list";
    }
}