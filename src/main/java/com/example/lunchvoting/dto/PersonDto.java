package com.example.lunchvoting.dto;

import com.example.lunchvoting.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.Set;

/**
 *
 */
public class PersonDto extends BaseDto {

    @NotBlank
    @Size(min = 2, max = 20, message = "length must be between 2 and 20 characters")
    private String username;

    @Email
    @NotBlank
    private String email;

    @Size(min = 5, max = 32, message = "length must between 5 and 32 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String firstName;

    private String lastName;

    private Set<Role> roles;

    public PersonDto(){}

    public PersonDto(Long id, String username, String email,  String password, String firstName, String lastName, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
