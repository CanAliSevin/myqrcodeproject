// src/main/java/com/qrscanapp/qrscanbackend/model/User.java (Kesin Düzeltme)
package com.qrscanapp.qrscanbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname; // <<< EKLENDİ

    @Column(nullable = false)
    private String lastname;  // <<< EKLENDİ

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Hashlenmiş şifre olacak

    @Column(nullable = false)
    private String company;

    // --- Constructor'lar ---
    public User() {
        // No-args constructor
    }

    // Tüm alanları içeren constructor
    public User(Long id, String firstname, String lastname, String username, String password, String company) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.company = company;
    }

    // --- Getter ve Setter Metotları ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() { // <<< EKLENDİ
        return firstname;
    }

    public void setFirstname(String firstname) { // <<< EKLENDİ
        this.firstname = firstname;
    }

    public String getLastname() { // <<< EKLENDİ
        return lastname;
    }

    public void setLastname(String lastname) { // <<< EKLENDİ
        this.lastname = lastname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    // --- UserDetails Metotları ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
