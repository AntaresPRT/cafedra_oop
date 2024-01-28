package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Audience;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.models.Group;
import ru.shtanko.spring.SecurityApp2.models.Schedule;
import ru.shtanko.spring.SecurityApp2.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AudienceService audienceService;
    private final EmployeeService employeeService;
    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, AudienceService audienceService, EmployeeService employeeService, GroupService groupService, CourseService courseService) {
        this.scheduleService = scheduleService;
        this.audienceService = audienceService;
        this.employeeService = employeeService;
        this.groupService = groupService;
        this.courseService = courseService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("schedules",scheduleService.findAll());
        return "schedule/index";
    }

    @GetMapping("/group/{id}")
    public String indexGroup(@PathVariable("id") int id,Model model) {
        List<Schedule> groupList = new ArrayList<>();
        String titleGroup = groupService.findOne(id).getTitle();
        for(Schedule schedule : scheduleService.findAll()) {
            if(schedule.getGroup().getTitle().equals(titleGroup)) {
                groupList.add(schedule);
            }
        }

        model.addAttribute("schedules",groupList);
        return "schedule/indexSearch";
    }

    @GetMapping("/audience/{id}")
    public String indexAudience(@PathVariable("id") int id,Model model) {
        List<Schedule> audienceList = new ArrayList<>();
        int audience = audienceService.findOne(id).getNumber();
        for(Schedule schedule : scheduleService.findAll()) {
            if(schedule.getAudience().getNumber() ==audience) {
                audienceList.add(schedule);
            }
        }

        model.addAttribute("schedules",audienceList);
        return "schedule/indexSearch";
    }

    @GetMapping("/employee/{id}")
    public String indexEmployee(@PathVariable("id") int id,Model model) {
        List<Schedule> employeeList = new ArrayList<>();
        Employee employee = employeeService.findOne(id);
        for(Schedule schedule : scheduleService.findAll()) {
            if(schedule.getEmployee().equals(employee)) {
                employeeList.add(schedule);
            }
        }

        model.addAttribute("schedules",employeeList);
        return "schedule/indexSearch";
    }

    @GetMapping("/searchGroup")
    public String searchGroupsByName(@RequestParam("search") String search, Model model) {
        List<Group> allGroups = groupService.findAll();


        List<Group> filteredGroups = allGroups.stream()
                .filter(group -> group.getTitle().contains(search))
                .collect(Collectors.toList());

        model.addAttribute("groups", filteredGroups);
        return "schedule/group";
    }

    @GetMapping("/searchAudience")
    public String searchAudienceByNumber(@RequestParam("search") int search, Model model) {
        List<Audience> allGroups = audienceService.findAll();


        List<Audience> filteredAudience = allGroups.stream()
                .filter(group -> group.getNumber() == search)
                .collect(Collectors.toList());

        model.addAttribute("audiences", filteredAudience);
        return "schedule/audience";
    }

    @GetMapping("/searchEmployee")
    public String searchEmployeeByName(@RequestParam("search") String search, Model model) {
        List<Employee> allEmployee = employeeService.findAll();

        List<Employee> filteredEmployee = allEmployee.stream()
                .filter(employee ->
                        employee.getLast_name().contains(search) ||
                                employee.getFirst_name().contains(search) ||
                                employee.getPatronymic().contains(search))
                .collect(Collectors.toList());

        model.addAttribute("employees", filteredEmployee);
        return "schedule/employee";
    }

    @GetMapping("/byDate/from")
    public String getScheduleByDate(@RequestParam("selectedFromDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate selectedFromDate, @RequestParam("selectedToDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate selectedToDate, Model model) {
        List<Schedule> allSchedule = scheduleService.findAll();

        List<Schedule> filteredSchedules = allSchedule.stream()
                .filter(group -> group.getDate().isAfter(selectedFromDate) && group.getDate().isBefore(selectedToDate))
                .collect(Collectors.toList());

        model.addAttribute("schedules", filteredSchedules);
        return "schedule/indexSearch";
    }

    @GetMapping("/group")
    public String listOfGroup(Model model) {
        model.addAttribute("groups",groupService.findAll());
        return "schedule/group";
    }

    @GetMapping("/audience")
    public String listOfAudience(Model model) {
        model.addAttribute("audiences",audienceService.findAll());
        return "schedule/audience";
    }

    @GetMapping("/employee")
    public String listOfEmployee(Model model) {
        model.addAttribute("employees",employeeService.findAll());
        return "schedule/employee";
    }


    @GetMapping("/new")
    public String newSchedule(@ModelAttribute("schedule") Schedule schedule, Model model) {
        model.addAttribute("audiences",audienceService.findAll());
        model.addAttribute("employees",employeeService.findAll());
        model.addAttribute("groups",groupService.findAll());
        model.addAttribute("courses",courseService.findAll());
        return "schedule/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.save(schedule);
        System.out.println(schedule.getDate().getDayOfWeek());
        return "redirect:/schedule";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        scheduleService.delete(id);
        return "redirect:/schedule";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model) {
        model.addAttribute("schedule",scheduleService.findOne(id));
        model.addAttribute("audiences",audienceService.findAll());
        model.addAttribute("employees",employeeService.findAll());
        model.addAttribute("groups",groupService.findAll());
        model.addAttribute("courses",courseService.findAll());
        return "schedule/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Schedule updatedSchedule) {
        scheduleService.update(id,updatedSchedule);
        return "redirect:/schedule";
    }
}
