package dd.mhja.dao;

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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name="leader_id")
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
