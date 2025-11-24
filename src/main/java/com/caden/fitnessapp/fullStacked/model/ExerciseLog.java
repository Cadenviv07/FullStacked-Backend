package com.caden.fitnessapp.fullStacked.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Exercise log")
public class ExerciseLog {
    @Id
    private String exerciseId;
    private String userId;

    private List<SetLog> sets;
    private String name;
    private String date; 


    public void setExerciseId(){
        this.ExerciseId = id;
    }
    public void setUserId(){
        this.UserId = id;
    }

    public String getUserId(){
        return userId;
    }

    public String getExerciseId(){
        return 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    class SetLog {
        private int setNumber;
        private double weight;
        private int reps;

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }

        public double getReps() { return reps; }
        public void setReps(int reps) { this.weight = reps; }

        public double getNumber() { return setNumber; }
        public void setNumber(int setNumber) { this.setNumber = setNumber; }
    }
}