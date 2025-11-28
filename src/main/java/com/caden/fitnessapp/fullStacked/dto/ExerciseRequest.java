package com.caden.fitnessapp.fullStacked.dto;

public class ExerciseRequest {
    private int reps; 
    private int sets;
    private String exercise;
    private double weight;


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
