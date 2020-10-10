package dd.mhja.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TEAMS")
public class Team {
    @Id
    // OracleDB 12+ / MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // OracleDB sequence
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TeamGen")
    @SequenceGenerator(sequenceName = "TEAM_SEQ", allocationSize = 1, name = "TeamGen")
    @Column(name = "TEAM_ID")
    private int id;

    // annotation not strictly required
    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name="LEADER_ID")
    private Coder leader;
    
    public Team() {
    }

    public Team(String name) {
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

    public Coder getLeader() {
        return leader;
    }

    public void setLeader(Coder leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", leader=" + leader + "]";
    }
}
