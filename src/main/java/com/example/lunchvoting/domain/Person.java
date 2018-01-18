package com.example.lunchvoting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;

/**
 *
 */
@NamedQueries({
        @NamedQuery(name = Person.ALL_SORTED, query = "SELECT p FROM Person p ORDER BY p.username")
})
@Cacheable
@Cache(region = "users", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "users")
public class Person extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Person.getAll";

    @NotBlank
    @Column(unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Size(min = 5)
    private String password;

    private Date registered = new Date();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    @BatchSize(size = 100)
    List<Vote> votes;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    Set<Role> roles;

    public Person() {
    }

    public Person(Long id, String username, String email, String password, Date registered, String firstName, String lastName, Set<Role> roles) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Person(Long id, String username, String email, String password, Date registered, Set<Role> roles) {
        this(id, username, email, password, registered, null, null, roles);
    }

    public Person(Long id, String username, String email, String password, String firstName, String lastName, Role role, Role... roles) {
        this(id, username, email, password, new Date(), firstName, lastName, EnumSet.of(role, roles));
    }

    public Person(Person person) {
        this(person.id, person.username, person.email, person.password, person.registered, person.firstName, person.lastName, person.roles);
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
