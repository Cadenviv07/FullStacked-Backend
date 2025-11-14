package com.caden.fitnessapp.fullStacked.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.caden.fitnessapp.fullStacked.model.Workout;

public class WorkoutResponse {
    private String name;
    private LocalDate date;
    private List<ExerciseResponse> exercises;

    public WorkoutResponse(Workout workout) {
        this.name = workout.getWorkout();
        this.date = workout.getDate();
        this.exercises = workout.getExercises()
            .stream()
            .map(ExerciseResponse::new)
            .collect(Collectors.toList());
    }

    // Getters
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public List<ExerciseResponse> getExercises() { return exercises; }
}
