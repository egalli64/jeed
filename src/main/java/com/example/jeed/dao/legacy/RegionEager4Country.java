/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * JPA entity for region in one to many EAGER relation with country
 * 
 * Are you really sure you want it?
 */
@Entity
@Table(name = "Region")
public class RegionEager4Country {
    @Id
    @Column(name = "REGION_ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<Country> countries;

    public RegionEager4Country() {
    }

    public RegionEager4Country(String name) {
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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + ", countries=" + countries + "]";
    }
}
