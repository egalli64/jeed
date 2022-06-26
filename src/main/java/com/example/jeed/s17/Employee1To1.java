package com.example.jeed.s17;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee1To1 {
    @Id
    @Column(name = "EMPLOYEE_ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne(optional = true, mappedBy = "employee")
    private Car1To1 car;

    public Employee1To1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Car1To1 getCar() {
        return car;
    }

    public void setCar(Car1To1 car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Employee1To1Car [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
