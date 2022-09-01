package com.pavlenko.payments.model.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private final String role;
    private String firstname;
    private String lastname;
    private String  status;
    private Date date;
    private final int id;

    public User(String role, int id, String firstname, String lastname, String status, Date date) {
        this.role = role;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;
        this.date = date;
    }

    public User(String role, int id) {
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "role=" + role +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
