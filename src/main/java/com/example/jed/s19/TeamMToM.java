package com.example.jed.s19;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "TEAMS")
public class TeamMToM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "TEAM_CODER", joinColumns = @JoinColumn(name = "TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "CODER_ID"))
    private Set<CoderMToM> coders;

    public TeamMToM() {
    }

    public TeamMToM(String name) {
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

    public Set<CoderMToM> getCoders() {
        return coders;
    }

    public void setCoders(Set<CoderMToM> coders) {
        this.coders = coders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Team [id=" + id + ", name=" + name);
        if (Hibernate.isInitialized(coders)) {
            sb.append(", coders=");
            sb.append(coders);
        }
        sb.append(']');
        return sb.toString();
    }
}
