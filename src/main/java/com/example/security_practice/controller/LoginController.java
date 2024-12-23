package com.example.security_practice.controller;

import com.example.security_practice.configuration.JWTUtils;
import com.example.security_practice.model.LoginData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginData loginData, HttpServletResponse httpServletResponse) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginData.getUser(), loginData.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        Cookie cookie = new Cookie("auth", jwtUtils.createJWTToken(authentication));
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok("login success");
    }

}
