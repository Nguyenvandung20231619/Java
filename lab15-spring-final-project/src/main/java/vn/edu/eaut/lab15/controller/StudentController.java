package vn.edu.eaut.lab15.controller;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.eaut.lab15.entity.Student;
import vn.edu.eaut.lab15.repository.StudentRepository;
@Controller @RequestMapping("/students")
public class StudentController {
    private final StudentRepository repo;
    public StudentController(StudentRepository repo) { this.repo=repo; }
    @GetMapping public String list(@RequestParam(defaultValue="") String keyword, Model model) {
        model.addAttribute("students",keyword.isBlank()?repo.findAll():repo.findByStudentCodeContainingIgnoreCaseOrFullNameContainingIgnoreCase(keyword,keyword)); model.addAttribute("keyword",keyword); return "students/list";
    }
    @GetMapping("/create") public String create(Model model) { model.addAttribute("student",new Student()); return "students/form"; }
    @GetMapping("/edit/{id}") public String edit(@PathVariable Long id, Model model) { model.addAttribute("student",repo.findById(id).orElseThrow()); return "students/form"; }
    @PostMapping("/save") public String save(@Valid @ModelAttribute Student student, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()) return "students/form";
        try { repo.save(student); redirect.addFlashAttribute("success","Lưu sinh viên thành công!"); } catch(Exception e) { redirect.addFlashAttribute("error","Mã sinh viên đã tồn tại"); }
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id, RedirectAttributes redirect) { repo.deleteById(id); redirect.addFlashAttribute("success","Xóa sinh viên thành công!"); return "redirect:/students"; }
}
