package com.store.model;

public class Customer {
    private int id;
    private String fname, lname, username, email;

    public Customer(int id, String fname, String lname, String username, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fname;
    }

    public void setLName() {
        this.lname = lname;
    }

    public String getLName() {
        return lname;
    }

    public void setUsername() {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {

        return String.format(
                "Customer[id=%d, fname='%s', lname='%s', username='%s', email='%s']",
                id, fname, lname, username, email);
    }
}
