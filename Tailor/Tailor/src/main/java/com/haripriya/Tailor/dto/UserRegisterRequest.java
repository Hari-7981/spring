package com.haripriya.Tailor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
@Data
public class UserRegisterRequest {

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

    @NotBlank(message = "Password is required") // Password will be handled by a User/Auth service normally
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password; // This field is included for simple standalone service registration



}
