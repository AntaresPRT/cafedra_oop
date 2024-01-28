package ru.shtanko.spring.SecurityApp2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"group\"")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id_group;

    @Column(name = "title")
    private String title;

    @Column(name = "course")
    private int course;

    @OneToMany(mappedBy = "group")
    private List<Schedule> schedules;

    public Group() {}

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
