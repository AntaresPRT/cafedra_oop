package ru.shtanko.spring.SecurityApp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.models.Position;
import ru.shtanko.spring.SecurityApp2.services.EmployeeService;
import ru.shtanko.spring.SecurityApp2.services.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees",employeeService.findAll());
        return "employee/index";
    }


    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee")Employee employee, Model model) {
        model.addAttribute("positions",positionService.findAll());
        return "employee/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model) {
        model.addAttribute("employee",employeeService.findOne(id));
        model.addAttribute("positions",positionService.findAll());
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id,Employee updatedEmployee) {
        employeeService.update(id,updatedEmployee);
        return "redirect:/employee";
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
        return "employee/indexSearch";
    }

}
