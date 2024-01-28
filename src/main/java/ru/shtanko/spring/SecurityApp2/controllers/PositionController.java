package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Group;
import ru.shtanko.spring.SecurityApp2.models.Position;
import ru.shtanko.spring.SecurityApp2.services.PositionService;

@Controller
@RequestMapping("/position")
public class PositionController {


    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("positions",positionService.findAll());
        return "position/index";
    }


    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("position") Position position) {
        return "position/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("position") Position position) {
        positionService.save(position);
        return "redirect:/position";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        positionService.delete(id);
        return "redirect:/position";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("position",positionService.findOne(id));
        return "position/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Position updatedPosition) {
        positionService.update(id,updatedPosition);
        return "redirect:/position";
    }
}
