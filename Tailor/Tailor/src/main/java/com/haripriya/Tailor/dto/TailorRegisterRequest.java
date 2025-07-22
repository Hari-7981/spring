package com.haripriya.Tailor.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Lombok: Generates getters, setters, toString, equals, hashCode


@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
public class TailorRegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String username;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be a 10-digit number")
    private String phoneNumber; // Corresponds to 'mobile' on frontend

    @NotBlank(message = "shopName is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String shopName;

    @NotNull(message = "Years of experience is required")
    @Min(value = 0, message = "Experience years cannot be negative")
    private Integer experienceYears; // Corresponds to 'exp' on frontend

    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio; // Corresponds to 'bio' on frontend

    @NotBlank(message = "Location is required")
    private String location; // Corresponds to 'location' on frontend

    @NotBlank(message = "Password is required") // Password will be handled by a User/Auth service normally
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password; // This field is included for simple standalone service registration


    @Size( message = "Profile image URL cannot exceed 500 characters")
    private String profileImageUrl;


    @Size( message = "At least 3 work samples are required")
    private List<String> works = new ArrayList<>();
}


