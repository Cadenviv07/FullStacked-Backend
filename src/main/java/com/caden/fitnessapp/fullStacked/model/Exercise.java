package com.caden.fitnessapp.fullStacked.model;



public class Exercise {

    private String id;

    private List<SetLog> sets; 
    private int sets;
    private String exercise;
    private double weight;

    //Filled in from api 
    private String muscleGroup;
    private String equipment;
    
    public String getId() {
        return id;
    }

    public String setId(){
        this.id = id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
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

    public void setLog(List<SetLog> setLog){
        this.sets = setLog;
    }

    public List<SetLog> getSets(){
        return sets;
    }
}   
