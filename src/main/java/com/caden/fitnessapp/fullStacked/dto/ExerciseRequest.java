package com.caden.fitnessapp.fullStacked.dto;

import com.caden.fitnessapp.fullStacked.model.Exercise.SetLog;

import java.util.List;

public class ExerciseRequest {
    private List<SetLog> sets;
    private String exercise;
    private double weight;
    
    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLog(List<SetLog> setLog){
        this.sets = setLog;
    }

    public List<SetLog> getSets(){
        return sets;
    }
}
