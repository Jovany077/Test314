package ru.kata.spring.boot_security.demo.DTO;

import java.util.Set;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Set<Long> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String password, Set<Long> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
