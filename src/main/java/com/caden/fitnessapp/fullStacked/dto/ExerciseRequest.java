package com.caden.fitnessapp.fullStacked.dto;

import java.util.List;

public class ExerciseRequest {
    private List<SetLogDto> sets;
    private String exercise;
    private double weight;
    private String roe;

    public static class SetLogDto{
        private int setNumber;
        private double weight;
        private int reps;

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }

        public int getReps() { return reps; }
        public void setReps(int reps) { this.weight = reps; }

        public double getNumber() { return setNumber; }
        public void setNumber(int setNumber) { this.setNumber = setNumber; }
    }
    
    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    
    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe){
        this.roe = roe;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLog(List<SetLogDto> setLog){
        this.sets = setLog;
    }

    public List<SetLogDto> getSets(){
        return sets;
    }
}
