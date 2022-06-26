package com.example.jeed.s15;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAR")
public class CarGV {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CarGen")
    @SequenceGenerator(sequenceName = "CAR_SEQ", allocationSize = 1, name = "CarGen")
    @Column(name = "CAR_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    public CarGV() {
    }

    public CarGV(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarGV [id=" + id + ", name=" + name + "]";
    }
}
