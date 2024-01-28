package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Group;
import ru.shtanko.spring.SecurityApp2.services.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("groups",groupService.findAll());
        return "group/index";
    }


    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("group") Group group) {
        return "group/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/group";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        groupService.delete(id);
        return "redirect:/group";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("group",groupService.findOne(id));
        return "group/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Group updatedGroup) {
        groupService.update(id,updatedGroup);
        return "redirect:/group";
    }

    @GetMapping("/searchGroup")
    public String searchGroupsByName(@RequestParam("search") String search, Model model) {
        List<Group> allGroups = groupService.findAll();


        List<Group> filteredGroups = allGroups.stream()
                .filter(group -> group.getTitle().contains(search))
                .collect(Collectors.toList());

        model.addAttribute("groups", filteredGroups);
        return "group/indexSearch";
    }
}
