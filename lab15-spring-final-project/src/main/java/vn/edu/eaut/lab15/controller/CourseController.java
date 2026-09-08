package vn.edu.eaut.lab15.controller;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.eaut.lab15.entity.Course;
import vn.edu.eaut.lab15.repository.CourseRepository;
@Controller @RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repo;
    public CourseController(CourseRepository repo) { this.repo=repo; }
    @GetMapping public String list(@RequestParam(defaultValue="") String keyword, Model model) {
        model.addAttribute("courses",keyword.isBlank()?repo.findAll():repo.findByCourseCodeContainingIgnoreCaseOrCourseNameContainingIgnoreCase(keyword,keyword)); model.addAttribute("keyword",keyword); return "courses/list";
    }
    @GetMapping("/create") public String create(Model model) { model.addAttribute("course",new Course()); return "courses/form"; }
    @GetMapping("/edit/{id}") public String edit(@PathVariable Long id, Model model) { model.addAttribute("course",repo.findById(id).orElseThrow()); return "courses/form"; }
    @PostMapping("/save") public String save(@Valid @ModelAttribute Course course, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()) return "courses/form";
        try { repo.save(course); redirect.addFlashAttribute("success","Lưu môn học thành công!"); } catch(Exception e) { redirect.addFlashAttribute("error","Mã môn học đã tồn tại"); }
        return "redirect:/courses";
    }
    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        try { repo.deleteById(id); redirect.addFlashAttribute("success","Xóa môn học thành công!"); } catch(Exception e) { redirect.addFlashAttribute("error","Không thể xóa môn học đang có đăng ký"); }
        return "redirect:/courses";
    }
}
