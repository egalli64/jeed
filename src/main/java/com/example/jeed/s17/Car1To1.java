package com.example.jeed.s17;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car1To1 {
    @Id
    @Column(name = "CAR_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = true)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee1To1 employee;

    public Car1To1() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee1To1 getEmployee() {
        return employee;
    }

    public void setEmployee(Employee1To1 employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car1To1Employee [id=" + id + ", name=" + name + ", employee=" + employee + "]";
    }
}
