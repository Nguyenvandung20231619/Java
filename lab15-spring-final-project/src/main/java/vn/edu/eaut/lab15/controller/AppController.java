package vn.edu.eaut.lab15.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.eaut.lab15.service.AppServices;

@Controller
public class AppController {
    private final AppServices appServices;
    public AppController(AppServices appServices) { this.appServices = appServices; }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", appServices.countStudents());
        model.addAttribute("totalCourses", appServices.countCourses());
        model.addAttribute("totalEnrollments", appServices.countEnrollments());
        return "dashboard/index";
    }
    @GetMapping("/login") public String login() { return "auth/login"; }
    @GetMapping("/403") public String accessDenied() { return "error/403"; }
}