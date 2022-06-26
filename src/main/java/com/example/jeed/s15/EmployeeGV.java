package com.example.jeed.s15;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JavaBean for EMPLOYEE, using identity - feature supported in PostgreSQL, MySQL and Oracle 12+
 */
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeGV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private int phone;

    @Column(name = "HIRED")
    private LocalDate hired;

    @Column(name = "JOB_ID")
    private int jobId;

    @Column(name = "SALARY")
    private double salary;

    public EmployeeGV() {
    }

    public EmployeeGV(String firstName, String lastName, int phone, double salary) {
        this(firstName, lastName, LocalDate.now(), phone, 15, salary);
    }

    public EmployeeGV(String firstName, String lastName, LocalDate hired, int phone, int jobId, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hired = hired;
        this.jobId = jobId;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public LocalDate getHired() {
        return hired;
    }

    public void setHireDate(LocalDate hired) {
        this.hired = hired;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeGV [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hired=" + hired
                + ", salary=" + salary + "]";
    }
}
