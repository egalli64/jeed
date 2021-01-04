package com.example.jed.s18;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REGIONS")
public class Region1ToM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private int id;

    @Column(name = "REGION_NAME")
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
