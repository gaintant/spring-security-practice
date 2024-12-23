package com.example.security_practice.model;

import lombok.*;

public class LoginData {
    String user;

    String password;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public LoginData(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
