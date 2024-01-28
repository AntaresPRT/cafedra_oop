package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.repositories.EmployeesRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeesRepository employeesRepository;

    @Autowired
    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> findAll() {
        return employeesRepository.findAll();
    }

    public Employee findOne(int id) {
        return employeesRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Employee employee) {
        employeesRepository.save(employee);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Employee updatedEmployee) {
        updatedEmployee.setEmployee_id(id);
        employeesRepository.save(updatedEmployee);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        employeesRepository.deleteById(id);
    }
}
