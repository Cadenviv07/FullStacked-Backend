package com.caden.fitnessapp.fullStacked.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "verification_codes")
public class VerificationCode {   
    @Id
    private String id;

    private String code;

    private LocalDateTime expiryDate;

    private String userId;

    public VerificationCode(){}

    public VerificationCode(String code, LocalDateTime expiryDate, User user){
        this.code = code;
        this.expiryDate = expiryDate;
    }

    public String getId() { return id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId;}
}
