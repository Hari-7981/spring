package com.haripriya.Tailor.dto;



import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok: Generates getters, setters, toString, equals, hashCode
public class TailorProfileResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer experienceYears;
    private String bio;
    private String location;
    private LocalDateTime createdAt;
    // You might add average rating, verified status etc. here later
}