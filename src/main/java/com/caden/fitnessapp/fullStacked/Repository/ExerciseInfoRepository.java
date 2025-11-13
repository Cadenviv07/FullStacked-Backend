package com.caden.fitnessapp.fullStacked.repository;

import com.caden.fitnessapp.fullStacked.model.Exercise;
;

import java.util.Optional;

@Repository
public interface ExerciseInfoRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByNameIgnoreCase(String name);
}

