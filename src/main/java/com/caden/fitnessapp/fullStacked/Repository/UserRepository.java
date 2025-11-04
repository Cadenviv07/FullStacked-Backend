package com.caden.fitnessapp.fullStacked.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caden.fitnessapp.fullStacked.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Finds a user by their email
    Optional<User> findByEmail(String email);
    
    // Finds a user by their username
    Optional<User> findByUsername(String username);
}

