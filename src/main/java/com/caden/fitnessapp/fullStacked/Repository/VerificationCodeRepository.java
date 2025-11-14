package com.caden.fitnessapp.fullStacked.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.caden.fitnessapp.fullStacked.model.VerificationCode;

public interface VerificationCodeRepository extends MongoRepository<VerificationCode, String> {
    Optional<VerificationCode> findByCode(String code);
}

