/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * JPA entity for the CAR table in an optional one to one relation with EMPLOYEE
 * by EMPLOYEE_ID
 * 
 * @see Employee4Car the other side of the one to one relation
 */
@Entity
public class Car {
    @Id
    @Column(name = "CAR_ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = true)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee4Car employee;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee4Car getEmployee() {
        return employee;
    }

    public void setEmployee(Employee4Car employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", name=" + name + ", employee=" + employee + "]";
    }
}
