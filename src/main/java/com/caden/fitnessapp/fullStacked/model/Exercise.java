package com.caden.fitnessapp.fullStacked.model;

public class Exercise {

    private String id;

    private int reps; 
    private int sets;
    private String exercise;
    private double weight;

    //Filled in from api 
    private String muscleGroup;
    private String equipment;
    
    public String getId() {
        return id;
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

    public String getMuscleGroup(){
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup){
        this.muscleGroup = muscleGroup;
    }

    public String getEquipment(){
        return equipment;
    }

       public void setEquipment(String equipment){
        this.equipment = equipment;
    }
}   
