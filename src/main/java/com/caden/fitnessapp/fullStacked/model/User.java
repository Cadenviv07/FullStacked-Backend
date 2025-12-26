package com.caden.fitnessapp.fullStacked.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    private List<Workout> workouts = new ArrayList<>();
    
    @Id
    private String id;

    private String username;


    private String password;
    private String role; // e.g. ROLE_USER or ROLE_ADMIN
    private String email;
    private boolean isEnabled;

    //get id 
    public String getId(){return id;}

    //get username 
    public String getUsername(){return username;}
    //set username
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    //get password
    public String getPassword(){return password;}
    //set password
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled(){return isEnabled;}

    public void setEnable(boolean enable){
        this.isEnabled = enable;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role;}
    
    public List<Workout> getWorkouts() { return workouts; }
    
    public void setWorkouts(List<Workout> workouts){
        this.workouts = workouts;
    }
}
