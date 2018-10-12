package com.store.model;

public class Customer {
    private String fname, lname, username, email;

    public Customer(String fname, String lname, String username, String email) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
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
                "Customer{ \"fname\":\"%s\", \"lname\":\"%s\", \"username\":\"%s\", \"email\":\"%s\" }",
                fname, lname, username, email);
    }
}
