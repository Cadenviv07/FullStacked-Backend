package com.caden.fitnessapp.fullStacked.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
   
    private String id;

    private String date;

    private String name;

    private List<Exercise> exercises = new ArrayList<>();

    private List<String> muscleTargets = new ArrayList<>();
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkout(){
        return name;
    }

    public void setWorkout(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }
    
    public List<Exercise> getExercises() { return exercises;}
    public List<String> getMuscleTargets() { return muscleTargets;}
}
