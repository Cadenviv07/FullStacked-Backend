package com.caden.fitnessapp.fullStacked.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workout {
   
    private String id;

    private LocalDate date;

    private String name;

    private List<Exercise> exercises = new ArrayList<>();
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
