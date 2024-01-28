package ru.shtanko.spring.SecurityApp2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "audience")
public class Audience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_audience")
    private int id_audience;

    @Column(name = "number")
    private int number;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "audience")
    private List<Schedule> schedules;

    public Audience() {}

    public int getId_audience() {
        return id_audience;
    }

    public void setId_audience(int id_audience) {
        this.id_audience = id_audience;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
