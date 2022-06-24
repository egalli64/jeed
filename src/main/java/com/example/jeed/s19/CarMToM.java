package com.example.jeed.s19;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "CAR")
public class CarMToM {
    @Id
    @Column(name = "CAR_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "cars")
    private Set<ServiceMToM> services;

    public CarMToM() {
    }

    public CarMToM(String name) {
        this.name = name;
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

    public Set<ServiceMToM> getServices() {
        return services;
    }

    public void setCoders(Set<ServiceMToM> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Car [id=" + id + ", name=" + name);
        if (Hibernate.isInitialized(services)) {
            sb.append(", coders=");
            sb.append(services);
        }
        sb.append(']');
        return sb.toString();
    }
}
