package com.caden.fitnessapp.fullStacked.Repository;

import java.util.Optional;
import com.caden.fitnessapp.fullStacked.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}


