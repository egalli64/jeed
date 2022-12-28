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
import jakarta.persistence.ManyToOne;

/**
 * JPA entity for the COUNTRY table in a many to one relation with REGION by
 * REGION_ID
 * 
 * @see Region4Country the "one" side of the relation
 */
@Entity
public class Country {
    @Id
    @Column(name = "COUNTRY_ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region4Country region;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region4Country getRegion() {
        return region;
    }

    public void setRegion(Region4Country region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", name=" + name + "]";
    }
}
