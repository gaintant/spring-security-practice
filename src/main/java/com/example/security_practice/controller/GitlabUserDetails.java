package com.example.security_practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/userDetails")
public class GitlabUserDetails {

    @GetMapping
    public ResponseEntity<String> gitlabInfo(Principal principal){
        System.out.println(principal.toString());
        return ResponseEntity.ok(principal.toString());
    }
}
