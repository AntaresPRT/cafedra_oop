package ru.shtanko.spring.SecurityApp2.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    int employee_id;

    @Column(name = "last_name")
    @NotEmpty(message = "Это поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Не меньше 2 символов и не больше 30")
    String last_name;

    @Column(name = "first_name")
    @NotEmpty(message = "Это поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Не меньше 2 символов и не больше 30")
    String first_name;

    @Column(name = "patronymic")
    @NotEmpty(message = "Это поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Не меньше 2 символов и не больше 30")
    String patronymic;

    @Column(name = "contract_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate contract_date;

    @Column(name = "experience")
    @Min(value = 0, message = "Это поле должно быть больше 0")
    int experience;

    @ManyToOne
    @JoinColumn(name = "id_position", referencedColumnName = "id_position")
    private Position position;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;

    @OneToMany(mappedBy = "employee")
    private List<Course> courseList;

    @OneToMany(mappedBy = "employee")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "employee")
    private List<TypeCourse> typeCourses;

    public Employee() {}

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getContract_date() {
        return contract_date;
    }

    public void setContract_date(LocalDate contract_date) {
        this.contract_date = contract_date;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
