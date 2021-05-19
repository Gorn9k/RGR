package org.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="users"
        ,catalog="talon_by"
        , uniqueConstraints = @UniqueConstraint(columnNames={"Login", "Password" , "Tel", "Mail", "Nomer_kartyi"})
)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class User extends AbstractEntity {

    @Column(name="Login", nullable=false, length=15)
    private String login;

    @Column(name="Password", nullable=false, length=64)
    private String password;

    @Column(name="Tel", length=20)
    private String tel;

    @Column(name="Mail", length=30)
    private String mail;

    @Column(name="Nomer_kartyi", nullable=false, length=7)
    private String nomer_kartyi;

    private String authority = "ROLE_USER";

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Set<Talon> talons;

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNomer_kartyi() {
        return nomer_kartyi;
    }

    public void setNomer_kartyi(String nomer_kartyi) {
        this.nomer_kartyi = nomer_kartyi;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name="Authority", length=10)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? "ROLE_USER" : authority;
    }

    public Set<Talon> getTalons() {
        return this.talons;
    }

    public void setTalons(Set<Talon> talons) {
        this.talons = talons;
    }

}


