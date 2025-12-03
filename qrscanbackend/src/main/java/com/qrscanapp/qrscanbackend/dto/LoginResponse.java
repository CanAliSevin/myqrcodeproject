package com.qrscanapp.qrscanbackend.dto;

public class LoginResponse {
    private Long id;
    private String username;
    private String company;
    private String firstname;
    private String lastname;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(Long id, String username, String company, String firstname, String lastname, String token) {
        this.id = id;
        this.username = username;
        this.company = company;
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
    }

    // --- Getter ve Setter Metotları ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
