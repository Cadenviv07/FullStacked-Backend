package com.caden.fitnessapp.fullStacked.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


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
