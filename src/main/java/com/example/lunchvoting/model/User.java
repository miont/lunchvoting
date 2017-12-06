package com.example.lunchvoting.model;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
public class User extends AbstractNamedEntity {


    private String email;

    private String password;

    private Date registered = new Date();

    List<Vote> votes;

    Set<Role> roles;

    public User() {
    }

    public User(Long id, String name, String email, String password, Date registered, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.roles = roles;
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
