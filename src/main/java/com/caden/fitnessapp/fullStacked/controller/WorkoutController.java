package com.caden.fitnessapp.fullStacked.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caden.fitnessapp.fullStacked.repository.UserRepository;
import com.caden.fitnessapp.fullStacked.dto.ExerciseRequest;
import com.caden.fitnessapp.fullStacked.dto.ExerciseRequest.SetLogDto;
import com.caden.fitnessapp.fullStacked.dto.ExerciseResponse;
import com.caden.fitnessapp.fullStacked.dto.WorkoutRequest;
import com.caden.fitnessapp.fullStacked.dto.WorkoutResponse;
import com.caden.fitnessapp.fullStacked.model.Exercise;
import com.caden.fitnessapp.fullStacked.model.User;
import com.caden.fitnessapp.fullStacked.model.Workout;
import com.caden.fitnessapp.fullStacked.service.ExerciseInfoService;

@RestController
@RequestMapping("/workouts")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkoutController{
    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ExerciseInfoService exerciseInfoService;

    public ResponseEntity<?> createWorkout(@RequestBody WorkoutRequest workoutRequest) {
        System.out.println("--- START CREATE WORKOUT ---");
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("1. Authenticated User: " + username);

            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: User " + username + " not found in DB"));
            System.out.println("2. User found in DB: " + user.getEmail());


            Workout workout = new Workout();
            workout.setWorkout(workoutRequest.getWorkout());
            workout.setDate(workoutRequest.getDate());
            
            user.getWorkouts().add(workout);
            System.out.println("4. Workout added to list locally");

            userRepository.save(user);
            System.out.println("5. User saved to MongoDB successfully");

            return ResponseEntity.ok("Workout created successfully");

        } catch (Exception e) {
            System.err.println("!!! CRASH IN CONTROLLER !!!");
            e.printStackTrace(); // This prints the EXACT line number and error to your terminal
            return ResponseEntity.status(500).body("Internal Error: " + e.getMessage());
        }
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<String> addOrUpdateExercise(
            @PathVariable String workoutId, 
            @RequestBody ExerciseRequest request) {

    
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = user.getWorkouts().stream()
                .filter(w -> w.getId().equals(workoutId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Optional<Exercise> existingExercise = workout.getExercises().stream()
        
                .filter(e -> e.getExercise().equalsIgnoreCase(request.getExercise())) 
                .findFirst();

        Exercise exercise;
        boolean isNew = !existingExercise.isPresent();

        if (isNew) {
            exercise = new Exercise();
            exercise.setExercise(request.getExercise());
    
            exercise.setLog(new ArrayList<>()); 
            workout.getExercises().add(exercise); 
        } else {
            exercise = existingExercise.get();
      
            exercise.setLog(new ArrayList<>()); 
        }

        exercise = exerciseInfoService.getExerciseInfo(exercise); 
        

        

        if (!workout.getMuscleTargets().contains(exercise.getMuscleGroup())) {
            workout.getMuscleTargets().add(exercise.getMuscleGroup());
        }


        if (request.getSets() != null) {
            for (int i = 0; i < request.getSets().size(); i++) {
               
                SetLogDto setDto = request.getSets().get(i);
                
                Exercise.SetLog setEntity = new Exercise.SetLog(); 
                
                setEntity.setNumber(i + 1); 
                setEntity.setWeight(setDto.getWeight());
                setEntity.setReps(setDto.getReps());
                
             
                exercise.getSets().add(setEntity);
            }
        }

  
        userRepository.save(user);

        return ResponseEntity.ok(isNew ? "Exercise created" : "Exercise updated");
    }


    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user.getWorkouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(@PathVariable String id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Workout workout = user.getWorkouts().stream()
            .filter(w -> w.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Workout not found"));

        WorkoutResponse response = new WorkoutResponse(workout);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<ExerciseResponse> getExerciseById(@PathVariable String id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Workout workout = user.getWorkouts().stream()
            .filter(w -> w.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Workout not found"));

        Exercise exercise = workout.getExercises().stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Exercise not found"));
        
        ExerciseResponse response = new ExerciseResponse(exercise);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable String id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        boolean removed = user.getWorkouts().removeIf(w -> w.getId().equals(id));
        if (!removed) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
        
        userRepository.save(user);
        return ResponseEntity.ok("Workout deleted succesfully");
    }


    @DeleteMapping("/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable String workoutId , @PathVariable String exerciseId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = user.getWorkouts().stream()
            .filter(w -> w.getId().equals(workoutId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Workout not found"));

        boolean removed = workout.getExercises().removeIf(e -> e.getId().equals(exerciseId));
        if(!removed) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");

        userRepository.save(user);
        return ResponseEntity.ok("Exercise deleted succesfully");
    }

    @GetMapping("/progress/{workoutName}")
    public ResponseEntity<List<ExerciseResponse>> getProgress(@PathVariable String name){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        //In case another error occurs this is whats happening in case forgotten
        //Flatten all workouts exercises into one big stream and then match any exercises that match the name
        List<Exercise> matching = user.getWorkouts().stream()
            .flatMap(w -> w.getExercises().stream())
            .filter(e -> e.getExercise().equalsIgnoreCase(name))
            .toList();

        //Sreams all exercises then maps them to an exerciseResponse constructor then collects all of the dtos into a list
        List<ExerciseResponse> response = matching
            .stream()
            .map(ExerciseResponse::new)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
