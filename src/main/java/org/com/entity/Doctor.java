package org.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="doctors"
        ,catalog="talon_by"
        , uniqueConstraints = @UniqueConstraint(columnNames= {"Tel", "Nomer_kab"})
)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Doctor extends AbstractEntity {

    @Column(name="Surname", nullable=false, length=20)
    private String surname;

    @Column(name="Name", nullable=false, length=20)
    private String name;

    @Column(name="Otchestvo", length=20)
    private String otchestvo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_institution", nullable=false)
    @JsonIgnoreProperties(value = {"id", "address", "tel", "mail", "doctors", "talons"}, allowSetters = true)
    private Institution institution;

    @Column(name="Dolznost", nullable=false, length=15)
    private String dolznost;

    @Column(name="Tel", unique=true, length=20)
    private String tel;

    @Column(name="Nomer_kab", unique=true, nullable=false)
    private int nomerKab;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="doctor", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"doctor", "institution", "nomerKab"}, allowSetters = true)
    private Set<Talon> talons;

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return this.otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getDolznost() {
        return this.dolznost;
    }

    public void setDolznost(String dolznost) {
        this.dolznost = dolznost;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getNomerKab() {
        return this.nomerKab;
    }

    public void setNomerKab(int nomerKab) {
        this.nomerKab = nomerKab;
    }

    public Set<Talon> getTalons() {
        return this.talons;
    }

    public void setTalons(Set<Talon> talons) {
        this.talons = talons;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}


