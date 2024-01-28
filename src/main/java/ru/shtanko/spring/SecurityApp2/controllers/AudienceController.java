package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Audience;
import ru.shtanko.spring.SecurityApp2.models.Schedule;
import ru.shtanko.spring.SecurityApp2.services.AudienceService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/audience")
public class AudienceController {

    private final AudienceService audienceService;

    @Autowired
    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("audiences",audienceService.findAll());
        return "audience/index";
    }


    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("audience") Audience audience) {
        return "audience/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("schedule") Audience audience) {
        audienceService.save(audience);
        return "redirect:/audience";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        audienceService.delete(id);
        return "redirect:/audience";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("audience",audienceService.findOne(id));
        return "audience/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Audience updatedAudience) {
        audienceService.update(id,updatedAudience);
        return "redirect:/audience";
    }

    @GetMapping("/searchAudience")
    public String searchAudienceByNumber(@RequestParam("search") int search, Model model) {
        List<Audience> allGroups = audienceService.findAll();


        List<Audience> filteredAudience = allGroups.stream()
                .filter(group -> group.getNumber() == search)
                .collect(Collectors.toList());

        model.addAttribute("audiences", filteredAudience);
        return "audience/indexSearch";
    }
}
