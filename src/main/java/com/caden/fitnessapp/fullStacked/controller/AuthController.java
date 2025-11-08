package com.caden.fitnessapp.fullStacked.controller;

import com.caden.fitnessapp.fullStacked.model.User;
import com.caden.fitnessapp.fullStacked.service.UserService;
import com.caden.fitnessapp.fullStacked.service.VerificationService;


import jakarta.validation.Valid;

import com.caden.fitnessapp.fullStacked.Repository.UserRepository;
import com.caden.fitnessapp.fullStacked.dto.SignupRequest;
import com.caden.fitnessapp.fullStacked.dto.VerifyRequest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//This class handles http requests
@RestController
@Validated
//All methods inside the class will have /auth as their url
@RequestMapping("/api/auth")
public class authController {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private UserService service;

     private final VerificationService verificationService;

    public authController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;//Hash passwords before saving them 

    //This method is triggered by a POST request to /auth/register
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));//Encrypts password
        user.setEmail(signupRequest.getEmail());
        user.setRole("ROLE_USER");//set default role 
        user.setEnable(false);
        userRepository.save(user);//save user 

        return ResponseEntity.ok(Map.of("message", "Register succsefull"));
    }

    @PostMapping("/sendVerification")
    public ResponseEntity<String> sendVerification(@RequestBody VerifyRequest verifyRequest) {
        User user = userRepository.findByEmail(verifyRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        verificationService.createVerificationCode(user);
        return ResponseEntity.ok("Verification email sent!");
    }


    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyCode(@RequestBody VerifyRequest verifyRequest){

        boolean isVerified = verificationService.verifyCode(verifyRequest.getEmail(), verifyRequest.getCode());

        if(isVerified){
            return ResponseEntity.ok(Map.of("message", "Email verified"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid or expired verification code"));
        }

    }


    
    @PostMapping("/login")
    public String login(@RequestBody User user){

        return service.verify(user);

    }

}
