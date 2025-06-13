/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import java.util.Objects;

/**
 * A region JavaBean-like meant for classic Hibernate mapping
 * <p>
 * See the matching hbm.xml file for the mapping rules
 */
public class NativeRegion {
    private Integer id;
    private String name;

    public NativeRegion() {
    }

    public NativeRegion(String name) {
        this.name = name;
    }

    public NativeRegion(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NativeRegion other = (NativeRegion) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }
}
