package vn.edu.eaut.lab11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.eaut.lab11.model.Course;
import vn.edu.eaut.lab11.repository.CourseRepository;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("isEdit", false);
        return "course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseRepository.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        return courseRepository.findById(id)
                .map(course -> {
                    model.addAttribute("course", course);
                    model.addAttribute("isEdit", true);
                    return "course-form";
                })
                .orElse("redirect:/courses");
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") String id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
        return "redirect:/courses";
    }
}