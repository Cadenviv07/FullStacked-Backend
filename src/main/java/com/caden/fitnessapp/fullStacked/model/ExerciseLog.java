package com.caden.fitnessapp.fullStacked.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Exercise log")
public class ExerciseLog {
    @Id
    private String ExerciseId;
    private String userId;

    private int sets;
    private int reps;
    private String name;
    private String date; 
    private double weight;

    public String setExerciseId(){
        this.ExerciseId = id;
    }
    public String setUserId(){
        this.UserId = id;
    }


    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}