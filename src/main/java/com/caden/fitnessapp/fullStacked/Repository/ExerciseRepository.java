package com.caden.fitnessapp.fullStacked.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.caden.fitnessapp.fullStacked.model.Exercise;
import com.caden.fitnessapp.fullStacked.model.User;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkoutUserAndExercise(User user, String name);
}

