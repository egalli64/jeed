package com.example.jeed.s19;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "CODERS")
public class CoderMToM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODER_ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany(mappedBy = "coders")
    private Set<TeamMToM> teams;

    public CoderMToM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<TeamMToM> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamMToM> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Coder [id=" + id);
        sb.append(", firstName=" + firstName);
        sb.append(", lastName=" + lastName);

        if (Hibernate.isInitialized(teams)) {
            sb.append(", teams=");
            String s = teams.stream().map(TeamMToM::getName).collect(Collectors.joining(","));
            sb.append(s);
        }
        sb.append("]");

        return sb.toString();
    }
}
