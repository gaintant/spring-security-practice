package com.example.security_practice.repository;

import com.example.security_practice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetails extends JpaRepository<Users, String> {

}
