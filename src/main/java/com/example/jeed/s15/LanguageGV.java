package com.example.jeed.s15;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LANGUAGES")
public class LanguageGV {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LanGen")
    @SequenceGenerator(sequenceName = "LANGUAGE_SEQ", allocationSize = 1, name = "LanGen")
    @Column(name = "LANGUAGE_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    public LanguageGV() {
    }

    public LanguageGV(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Language15 [id=" + id + ", name=" + name + "]";
    }
}
