package com.example.lunchvoting.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "users")
public class Person extends AbstractBaseEntity {

    private String username;

    private String email;

    private String password;

    private Date registered = new Date();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany
    List<Vote> votes;


    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    Set<Role> roles;

    public Person() {
    }

    public Person(Long id, String name, String email, String password, Date registered, Set<Role> roles, String firstName, String lastName) {
        super(id);
        this.username = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Person(Long id, String name, String email, String password, Date registered, Set<Role> roles) {
        this(id, name, email, password, registered, roles, "", "");
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
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
