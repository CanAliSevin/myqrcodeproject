package com.qrscanapp.qrscanbackend.dto;

public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String company;


    public RegisterRequest() {
    }

    public RegisterRequest(String firstname, String lastname,String username, String password, String company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.company = company;
    }

    // Getters
     public String getFirstname() {
            return firstname;
       }
    public String getLastname() {
        return lastname;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    // Setters
    public void setFirstname(String firstname) {this.firstname = firstname;
    }
    public void setLastname(String lastname) {this.lastname = lastname;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}