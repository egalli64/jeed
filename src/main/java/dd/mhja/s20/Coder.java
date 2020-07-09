package dd.mhja.s20;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity(name = "CoderS20")
@Table(name = "CODERS")
public class Coder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coder_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    private Double salary;

    @OneToOne(optional = true, mappedBy = "leader")
    private Team leadingTeam;

    @ManyToMany(mappedBy = "coders")
    private Set<Team> teams;

    public Coder() {
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Team getLeadingTeam() {
        return leadingTeam;
    }

    public void setLeadingTeam(Team leadingTeam) {
        this.leadingTeam = leadingTeam;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Coder [id=" + id);
        sb.append(", firstName=" + firstName);
        sb.append(", lastName=" + lastName);
        sb.append(", hireDate=" + hireDate);
        sb.append(", salary=" + salary);

        if (leadingTeam != null) {
            sb.append(", leadingTeam=" + leadingTeam.getName());
        }
        if (Hibernate.isInitialized(teams)) {
            sb.append(", teams=");
            String s = teams.stream().map(Team::getName).collect(Collectors.joining(","));
            sb.append(s);
        }
        sb.append("]");

        return sb.toString();
    }
}
