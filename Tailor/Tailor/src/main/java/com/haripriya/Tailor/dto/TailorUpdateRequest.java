package com.haripriya.Tailor.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
@Data
public class TailorUpdateRequest {


    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;


    @Email(message = "Invalid email format")
    private String email;





    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be a 10-digit number")
    private String phoneNumber;


    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String shopName;


    @Min(value = 0, message = "Experience years cannot be negative")
    private Integer experienceYears;

    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;

    private String location;




}
