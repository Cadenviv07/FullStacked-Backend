package com.caden.fitnessapp.fullStacked.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.caden.fitnessapp.fullStacked.repository.ExerciseInfoRepository;
import com.caden.fitnessapp.fullStacked.model.Exercise;
import java.net.http.*;
import java.net.URI;
import java.util.Optional;

@Service
public class ExerciseInfoService {

    private final Gson gson = new Gson();

    @Value("${exercisedb.api.key}")
    private String apiKey;

    @Value("${exercisedb.api.host}")
    private String apiHost;

    @Autowired
    private ExerciseInfoRepository exerciseInfoRepository;

    public Exercise getExerciseInfo(Exercise exercise) {
        Optional<Exercise> existing = exerciseInfoRepository.findByExerciseIgnoreCase(exercise.getExercise());

        if (existing.isPresent()) {
            Exercise dbExercise = existing.get();
            
            exercise.setMuscleGroup(dbExercise.getMuscleGroup());
            exercise.setEquipment(dbExercise.getEquipment());

        } else {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://exercisedb.p.rapidapi.com/exercises/name/" + exercise.getExercise().trim().toLowerCase()))
                        .header("x-rapidapi-key", apiKey)
                        .header("x-rapidapi-host", apiHost)
                        .GET()
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Exercise[] apiResults = gson.fromJson(response.body(), Exercise[].class);
                if (apiResults.length > 0) {
                    Exercise apiExercise = apiResults[0];

                    
                    exercise.setMuscleGroup(apiExercise.getMuscleGroup());
                    exercise.setEquipment(apiExercise.getEquipment());

                    exerciseInfoRepository.save(apiExercise);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return exercise;
    }
}

