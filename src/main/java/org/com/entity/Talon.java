package org.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="talons"
        ,catalog="talon_by"
        , uniqueConstraints = @UniqueConstraint(columnNames="Nomer_kab")
)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Talon extends AbstractEntity {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_doctor", nullable=false)
    @JsonIgnoreProperties(value = {"id", "institution", "name", "otchestvo", "nomerKab", "tel", "talons"}, allowSetters = true)
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_user")
    @JsonIgnoreProperties(value = {"id", "password", "authority", "talons"}, allowSetters = true)
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_institution", nullable=false)
    @JsonIgnoreProperties(value = {"id", "tel", "mail", "doctors", "talons"}, allowSetters = true)
    private Institution institution;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Date_time", nullable=false)
    private Date date;

    @Column(name="Nomer_kab", unique=true, nullable=false)
    private int nomerKab;

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNomerKab() {
        return this.nomerKab;
    }

    public void setNomerKab(int nomerKab) {
        this.nomerKab = nomerKab;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}


