package com.example.jeed.s05;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeePlain {
    @Id
    @Column(name = "EMPLOYEE_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "HIRED")
    private LocalDate hired;

    @Column(name = "SALARY")
    private double salary;

    public EmployeePlain() {
    }

    public EmployeePlain(long id, String firstName, String lastName, double salary) {
        this(id, firstName, lastName, LocalDate.now(), salary);
    }

    public EmployeePlain(long id, String firstName, String lastName, LocalDate hired, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hired = hired;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getHired() {
        return hired;
    }

    public void setHired(LocalDate hired) {
        this.hired = hired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeePlain [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hired=" + hired
                + ", salary=" + salary + "]";
    }
}