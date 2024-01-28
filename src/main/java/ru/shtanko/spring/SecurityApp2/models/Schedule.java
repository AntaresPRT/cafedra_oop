package ru.shtanko.spring.SecurityApp2.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private int id_schedule;

    @Column(name = "day_of_week")
    private String day_of_week;

    @Column(name = "start_time")
    private Time start_time;

    @Column(name = "end_time")
    private Time end_time;

    @ManyToOne
    @JoinColumn(name = "id_audience", referencedColumnName = "id_audience")
    private Audience audience;
    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id_course")
    private Course course;

    @Column(name = "date")
    private LocalDate date;

    public Schedule() {}

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
