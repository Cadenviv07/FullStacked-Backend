package com.caden.fitnessapp.fullStacked.dto;

import java.util.List;

import com.caden.fitnessapp.fullStacked.model.Exercise.SetLog;
import com.caden.fitnessapp.fullStacked.model.Exercise;


public class ExerciseResponse {
    private String exercise;
    private List<SetLog> sets;
    private int reps;
    private double weight;
    private String muscleGroup;
    private String equipment;


    public ExerciseResponse(Exercise exercise) {
        this.exercise = exercise.getExercise();
        this.sets = exercise.getSets();
        this.muscleGroup = exercise.getMuscleGroup();
        this.equipment = exercise.getEquipment();
    }

    public String getExercise() { return exercise; }
    public List<SetLog> getSets(){ return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }
    public String getMuscleGroup() { return muscleGroup; }
    public String getEquipment() { return equipment; }
}
