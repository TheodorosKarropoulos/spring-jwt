package com.tkarropoulos.jwtdemo.security.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ApplicationUser extends PersistableEntity implements Serializable{

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public ApplicationUser() {
    }


    public ApplicationUser(String email, String username, String password, String firstName, String lastName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        ApplicationUser user = (ApplicationUser) obj;

        if(getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() != null)
            return false;

        return true;
    }
}
