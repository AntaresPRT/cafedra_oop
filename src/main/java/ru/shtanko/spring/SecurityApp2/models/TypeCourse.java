package ru.shtanko.spring.SecurityApp2.models;

import javax.persistence.*;

@Entity
@Table(name = "type_course")
public class TypeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_course")
    private int id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id_course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee employee;

    public TypeCourse() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
