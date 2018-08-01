package com.tkarropoulos.jwtdemo.security.domain;

import com.tkarropoulos.jwtdemo.utils.PersistableEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_USER")
public class ApplicationUser extends PersistableEntity implements Serializable{

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    protected ApplicationUser() {
    }

    public ApplicationUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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
