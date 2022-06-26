package com.example.jeed.s18;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "REGION")
public class Region1ToM {
    @Id
    @Column(name = "REGION_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "region")
    private Set<CountryMTo1> countries;

    public Region1ToM() {
    }

    public Region1ToM(String name) {
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

    public Set<CountryMTo1> getCountries() {
        return countries;
    }

    public void setCountries(Set<CountryMTo1> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + ", countries=" + countries + "]";
    }
}
