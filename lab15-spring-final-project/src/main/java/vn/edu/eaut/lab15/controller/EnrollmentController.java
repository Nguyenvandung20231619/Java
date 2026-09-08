package vn.edu.eaut.lab15.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.eaut.lab15.service.AppServices;
import vn.edu.eaut.lab15.repository.StudentRepository;
import vn.edu.eaut.lab15.repository.CourseRepository;
@Controller @RequestMapping("/enrollments")
public class EnrollmentController {
    private final AppServices service; private final StudentRepository studentRepository; private final CourseRepository courseRepository;
    public EnrollmentController(AppServices service, StudentRepository students, CourseRepository courses) { this.service=service; studentRepository=students; courseRepository=courses; }
    @GetMapping public String list(Model model) { model.addAttribute("enrollments",service.getAllEnrollments()); return "enrollments/list"; }
    @GetMapping("/create") public String create(Model model) { model.addAttribute("students",studentRepository.findAll()); model.addAttribute("courses",courseRepository.findAll()); return "enrollments/form"; }
    @PostMapping("/save") public String save(@RequestParam Long studentId,@RequestParam Long courseId,RedirectAttributes redirect) {
        try { service.enroll(studentId,courseId); redirect.addFlashAttribute("success","Đăng ký thành công!"); return "redirect:/enrollments"; }
        catch(Exception e) { redirect.addFlashAttribute("error",e.getMessage()); return "redirect:/enrollments/create"; }
    }
    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id,RedirectAttributes redirect) { service.deleteEnrollment(id); redirect.addFlashAttribute("success","Đã hủy đăng ký!"); return "redirect:/enrollments"; }
}
