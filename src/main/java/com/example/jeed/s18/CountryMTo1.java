package com.example.jeed.s18;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class CountryMTo1 {
    @Id
    @Column(name = "COUNTRY_ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region1ToM region;

    public CountryMTo1() {
    }

    public CountryMTo1(String name) {
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

    public Region1ToM getRegion() {
        return region;
    }

    public void setRegion(Region1ToM region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", name=" + name + (region != null ? ", region=" + region.getName() : "") + "]";
    }
}
