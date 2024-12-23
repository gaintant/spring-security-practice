package com.example.security_practice.controller;

import com.example.security_practice.model.Users;
import com.example.security_practice.repository.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/add")
@RestController
public class addUser {
    @Autowired
    UserDetails userDetails;
    @GetMapping()
    public void solve(){
        Users user = new Users();
        user.setEmail("kaus");
        user.setPassword(new BCryptPasswordEncoder().encode("123"));
        user.setRoles(List.of("user", "admin"));
        userDetails.save(user);
    }
}
