/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * JPA entity for team in many to many relation with employee
 */
@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "TEAM_EMPLOYEE", //
            joinColumns = @JoinColumn(name = "TEAM_ID"), //
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private Set<Employee4Team> employees;

    public Team() {
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

    public Set<Employee4Team> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee4Team> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", employees=" + employees + "]";
    }
}
