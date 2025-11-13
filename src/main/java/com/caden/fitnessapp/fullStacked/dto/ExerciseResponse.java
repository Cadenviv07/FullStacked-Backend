package com.caden.fitnessapp.fullStacked.dto;

import com.caden.fitnessapp.fullStacked.model.Exercise;

public class ExerciseResponse {
    private String exercise;
    private int sets;
    private int reps;
    private double weight;
    private String muscleGroup;
    private String equipment;


    public ExerciseResponse(Exercise exercise) {
        this.exercise = exercise.getExercise();
        this.sets = exercise.getSets();
        this.reps = exercise.getReps();
        this.weight = exercise.getWeight();
        this.muscleGroup = exercise.getMuscleGroup();
        this.equipment = exercise.getEquipment();
    }

    // Getters
    public String getExercise() { return exercise; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }
    public String getMuscleGroup() { return muscleGroup; }
    public String getEquipment() { return equipment; }
}
