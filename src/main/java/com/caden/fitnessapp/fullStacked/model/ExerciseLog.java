package com.caden.fitnessapp.fullStacked.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

@Document(collection = "ExerciseLog")
public class ExerciseLog {
    @Id
    private String exerciseId;
    private String userId;

    private List<SetLogHistory> sets;
    private String name;
    private LocalDate date; 


    public void setExerciseId(String id){
        this.exerciseId = id;
    }
    public void setUserId(String id){
        this.userId = id;
    }

    public String getUserId(){
        return userId;
    }

    public String getExerciseId(){
        return exerciseId;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static class SetLogHistory{
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

    public void setLog(List<SetLogHistory> setLog){
        this.sets = setLog;
    }

    public List<SetLogHistory> getSets(){
        return sets;
    }
}