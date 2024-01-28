package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Audience;
import ru.shtanko.spring.SecurityApp2.models.Course;
import ru.shtanko.spring.SecurityApp2.services.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("courses",courseService.findAll());
        return "course/index";
    }


    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("course") Course course) {
        return "course/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("schedule") Course course) {
        courseService.save(course);
        return "redirect:/course";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        courseService.delete(id);
        return "redirect:/course";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("course",courseService.findOne(id));
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Course updatedCourse) {
        courseService.update(id,updatedCourse);
        return "redirect:/course";
    }
}
