package com.example.security_practice.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JWTUtils {
    @Value("${jwt.secretkey}")
    private  String secretkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.exp}")
    private Long exp;

    public String createJWTToken(Authentication authentication){
        String jwt = JWT.create()
                .withSubject(authentication.getName())
                .withClaim("roles", authentication.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secretkey));
        return jwt;
    }

    public boolean verifyToken(String value) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretkey)).withIssuer(issuer).build();
        try{
            jwtVerifier.verify(value);
        }
        catch (Exception e){
            return false;
        }
            return true;
    }
}
