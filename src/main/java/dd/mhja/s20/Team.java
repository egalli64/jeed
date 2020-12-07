package dd.mhja.s20;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity(name = "TeamS20")
@Table(name = "TEAMS")
public class Team {
    @Id
    // MySQL / OracleDB 12+
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // OracleDB sequence
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TeamGen20")
//    @SequenceGenerator(sequenceName = "TEAM_SEQ", allocationSize = 1, name = "TeamGen20")
    @Column(name = "TEAM_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "LEADER_ID")
    private Coder leader;

    @ManyToMany
    @JoinTable(name = "TEAM_CODER", joinColumns = @JoinColumn(name = "TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "CODER_ID"))
    private Set<Coder> coders;

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

    public Set<Coder> getCoders() {
        return coders;
    }

    public void setCoders(Set<Coder> coders) {
        this.coders = coders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Team [id=" + id + ", name=" + name + ", leader=" + leader);
        if (Hibernate.isInitialized(coders)) {
            sb.append(", coders=");
            sb.append(coders);
        }
        return sb.toString();
    }
}
