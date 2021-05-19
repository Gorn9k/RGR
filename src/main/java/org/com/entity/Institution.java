package org.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="institutions"
        ,catalog="talon_by"
        , uniqueConstraints = @UniqueConstraint(columnNames={"Name", "Address", "Tel", "Mail"})
)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Institution extends AbstractEntity{

    @Column(name="Name", nullable=false, length=35)
    private String name;

    @Column(name="Address", nullable=false, length=35)
    private String address;

    @Column(name="Tel", length=20)
    private String tel;

    @Column(name="Mail", length=30)
    private String mail;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="institution", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"institution", "talons"}, allowSetters = true)
    private Set<Doctor> doctors;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="institution", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"institution"}, allowSetters = true)
    private Set<Talon> talons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Talon> getTalons() {
        return talons;
    }

    public void setTalons(Set<Talon> talons) {
        this.talons = talons;
    }
}
