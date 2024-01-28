package ru.shtanko.spring.SecurityApp2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private int id_course;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee employee;

    @Column(name = "hours")
    private int hours;

    @OneToMany(mappedBy = "course")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "course")
    private List<TypeCourse> typeCourses;

    public Course() {}

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
