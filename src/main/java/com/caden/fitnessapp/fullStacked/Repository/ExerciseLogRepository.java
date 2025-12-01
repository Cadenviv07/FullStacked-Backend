package com.caden.fitnessapp.fullStacked.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.caden.fitnessapp.fullStacked.model.ExerciseLog;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseLogRepository extends MongoRepository<ExerciseLog, LocalDate> {
    
    List<ExerciseLog> findByUserIdAndDate(String userId, LocalDate date);

    List<ExerciseLog> findByUserIdandDateBetween(String userId, LocalDate startDate, LocalDate endDate);
}