package com.caden.fitnessapp.fullStacked.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class ExerciseInfo {
    private String exercise;
    private String muscleGroup;
    private String equipment;

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
}
