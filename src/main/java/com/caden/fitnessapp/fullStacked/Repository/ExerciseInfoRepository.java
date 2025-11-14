package com.caden.fitnessapp.fullStacked.repository;

import com.caden.fitnessapp.fullStacked.model.Exercise;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExerciseInfoRepository extends MongoRepository<Exercise, String> {
    Optional<Exercise> findByNameIgnoreCase(String name);
}

