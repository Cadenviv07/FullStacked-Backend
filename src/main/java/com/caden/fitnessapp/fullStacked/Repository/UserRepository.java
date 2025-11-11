package com.caden.fitnessapp.fullStacked.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;
import com.caden.fitnessapp.fullStacked.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}


