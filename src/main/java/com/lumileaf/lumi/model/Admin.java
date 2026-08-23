package com.lumileaf.lumi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin1") // your actual MySQL table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String role;
    private String dashboard; // Waiting / Withering / Rolling

    // Getters and Setters
    public int getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDashboard() { return dashboard; }
    public void setDashboard(String dashboard) { this.dashboard = dashboard; }
}