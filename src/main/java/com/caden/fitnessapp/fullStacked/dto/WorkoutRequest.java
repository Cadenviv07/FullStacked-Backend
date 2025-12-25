package com.caden.fitnessapp.fullStacked.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WorkoutRequest {
    private String workout;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWorkout(){
        return workout;
    }

    public void setWorkout(String name){
        this.workout = name;
    }

}
