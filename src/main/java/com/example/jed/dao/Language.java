package com.example.jed.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LANGUAGES")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LangGen")
    @SequenceGenerator(sequenceName = "LANGUAGE_SEQ", allocationSize = 1, name = "LangGen")
    @Column(name = "LANGUAGE_ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public Language(Integer id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Language [id=" + id + ", name=" + name + "]";
    }
}
