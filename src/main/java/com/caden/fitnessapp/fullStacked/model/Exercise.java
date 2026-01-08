package com.caden.fitnessapp.fullStacked.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;


public class Exercise {

    private String id;

    private List<SetLog> sets; 

    private String exercise;
    

    //Filled in from api 
    @JsonAlias("target")
    private String muscleGroup;
    private String equipment;
    
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public static class SetLog {
        private int setNumber;
        private double weight;
        private int reps;

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }

        public int getReps() { return reps; }
        public void setReps(int reps) { this.reps = reps; }

        public int getNumber() { return setNumber; }
        public void setNumber(int setNumber) { this.setNumber = setNumber; }
    }

    public void setLog(List<SetLog> setLog){
        this.sets = setLog;
    }

    public List<SetLog> getSets(){
        return sets;
    }
}   
