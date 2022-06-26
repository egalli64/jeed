package com.example.jeed.s19;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "SERVICE")
public class ServiceMToM {
    @Id
    @Column(name = "SERVICE_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "CAR_SERVICE", joinColumns = @JoinColumn(name = "SERVICE_ID"), inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
    private Set<CarMToM> cars;

    public ServiceMToM() {
    }

    public int getId() {
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

    public Set<CarMToM> getCars() {
        return cars;
    }

    public void setCars(Set<CarMToM> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Service [id=" + id);
        sb.append(", name=" + name);

        if (Hibernate.isInitialized(cars)) {
            sb.append(", cars=");
            String s = cars.stream().map(CarMToM::getName).collect(Collectors.joining(","));
            sb.append(s);
        }
        sb.append("]");

        return sb.toString();
    }
}
