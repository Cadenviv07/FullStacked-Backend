package com.caden.fitnessapp.fullStacked.Repository;

import com.caden.fitnessapp.fullStacked.model.Workout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
}
