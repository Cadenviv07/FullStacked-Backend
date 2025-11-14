package com.caden.fitnessapp.fullStacked.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caden.fitnessapp.fullStacked.repository.UserRepository;
import com.caden.fitnessapp.fullStacked.dto.ExerciseRequest;
import com.caden.fitnessapp.fullStacked.dto.ExerciseResponse;
import com.caden.fitnessapp.fullStacked.dto.WorkoutRequest;
import com.caden.fitnessapp.fullStacked.dto.WorkoutResponse;
import com.caden.fitnessapp.fullStacked.model.Exercise;
import com.caden.fitnessapp.fullStacked.model.User;
import com.caden.fitnessapp.fullStacked.model.Workout;
import com.caden.fitnessapp.fullStacked.service.ExerciseInfoService;

@RestController

@RequestMapping("/workouts")
public class WorkoutController{
    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ExerciseInfoService exerciseInfoService;

    @PostMapping
    public ResponseEntity<String> createWorkout(@RequestBody WorkoutRequest workoutRequest){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = new Workout();
        workout.setDate(workoutRequest.getDate());
        workout.setWorkout(workoutRequest.getWorkout());
        user.getWorkouts().add(workout);
        userRepository.save(user);
        
        return ResponseEntity.ok("Workout created succsesfully");
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<String> addExercise(@PathVariable String workoutId , @RequestBody ExerciseRequest exerciseRequest){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    
        Workout workout = user.getWorkouts().stream()
            .filter(w -> w.getId().equals(workoutId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Workout not found"));


        Exercise exercise = new Exercise();
        exercise.setReps(exerciseRequest.getReps());
        exercise.setWeight(exerciseRequest.getWeight());
        exercise.setExercise(exerciseRequest.getExercise());
        exercise.setSets(exerciseRequest.getSets());

        exercise = exerciseInfoService.getExerciseInfo(exercise);
        
        if(!workout.getMuscleTargets().contains(exercise.getMuscleGroup())){
            workout.getMuscleTargets().add(exercise.getMuscleGroup());
        }

        workout.getExercises().add(exercise);
        userRepository.save(user);

        return ResponseEntity.ok("Exercise created succesfully");
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
