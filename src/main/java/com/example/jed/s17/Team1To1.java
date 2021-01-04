package com.example.jed.s17;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEAMS")
public class Team1To1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "LEADER_ID")
    private Coder1To1 leader;

    public Team1To1() {
    }

    public Team1To1(String name) {
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

    public Coder1To1 getLeader() {
        return leader;
    }

    public void setLeader(Coder1To1 leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Team1To1 [id=" + id + ", name=" + name + ", leader=" + leader + "]";
    }
}
