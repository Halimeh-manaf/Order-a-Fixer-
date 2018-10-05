package com.example.manafnajdathalimah.firebase2;

public class Values_user {
    String email;
    String name;
    String fullname;
    String number;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {

        return email;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getNumber() {
        return number;
    }

    public Values_user(String email, String name, String fullname, String number) {

        this.email = email;
        this.name = name;
        this.fullname = fullname;
        this.number = number;
    }
}
