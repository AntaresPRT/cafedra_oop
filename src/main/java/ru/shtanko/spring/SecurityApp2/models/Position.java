package ru.shtanko.spring.SecurityApp2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @Column(name = "id_position")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_position;

    @Column(name = "title")
    private String title;

    @Column(name = "salary")
    private int salary;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position () {}

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
