package com.haripriya.Tailor.service;


import com.haripriya.Tailor.Repository.total.TotalProfileRepository;
import com.haripriya.Tailor.dto.MaterialSupplierRegisterRequest;
import com.haripriya.Tailor.dto.TailorRegisterRequest;
import com.haripriya.Tailor.dto.TailorUpdateRequest;
import com.haripriya.Tailor.dto.UserRegisterRequest;
import com.haripriya.Tailor.model.TailorProfile;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TailorProfileService {

    @Autowired
    private TotalProfileRepository tailorProfileRepository;

    // If this service was also hashing and storing passwords directly, you'd uncomment this:
     @Autowired
     private BCryptPasswordEncoder passwordEncoder;

    /**
     * Registers a new tailor profile based on the provided request data.
     * Handles basic uniqueness validation for email and phone number.
     * <p>
     * In a full microservice architecture:
     * 1. A separate "User Service" would handle the core user registration (username, password, email, phone, role).
     * 2. This "Tailor Service" would then receive a signal (e.g., via a message queue or a direct API call from the User Service)
     * with the new `userId` and the tailor-specific details.
     * 3. This service would then create the `TailorProfile`, linking it to the `userId`.
     * 4. Email and Phone Number might not be stored here but fetched from the User Service when needed.
     * <p>
     * For this standalone microservice, we include email and phone for simplicity of demonstration.
     *
     * @return
     */
    public Long registerTailor(@Valid TailorRegisterRequest request) {
        // Check for existing tailor profiles with the same email or phone number
        if (tailorProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered for a tailor profile!");
        }
        if (tailorProfileRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered for a tailor profile!");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // Create a new TailorProfile entity
        TailorProfile tailorProfile = new TailorProfile();
        tailorProfile.setName(request.getName());
        tailorProfile.setEmail(request.getEmail());
        tailorProfile.setPhoneNumber(request.getPhoneNumber());
        tailorProfile.setExperienceYears(request.getExperienceYears());
        tailorProfile.setBio(request.getBio());
        tailorProfile.setLocation(request.getLocation());
        tailorProfile.setUsername(request.getUsername());
        tailorProfile.setShopName(request.getShopName());
        tailorProfile.setPassword(encodedPassword);
        tailorProfile.setRole("TAILOR"); // Default role for tailors
        // If storing passwords directly in this service, hash it:
        // tailorProfile.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        TailorProfile savedUser=  tailorProfileRepository.save(tailorProfile);


        return savedUser.getId();

    }

    public Long registerUser(@Valid UserRegisterRequest request) {
        // Check for existing tailor profiles with the same email or phone number
        if (tailorProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered for a tailor profile!");
        }
        if (tailorProfileRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered for a tailor profile!");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // Create a new TailorProfile entity
        TailorProfile tailorProfile = new TailorProfile();
        tailorProfile.setUsername(request.getUsername());
        tailorProfile.setName(request.getName());
        tailorProfile.setEmail(request.getEmail());
        tailorProfile.setPhoneNumber(request.getPhoneNumber());
        tailorProfile.setPassword(encodedPassword);
        tailorProfile.setRole("USER"); // Default role for users

        // If storing passwords directly in this service, hash it:
        // tailorProfile.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        // Save the new tailor profile to the database
        TailorProfile savedUser= tailorProfileRepository.save(tailorProfile);

        // Convert the saved entity to a DTO for the response
        return savedUser.getId();

    }


    public void registerMaterial(@Valid MaterialSupplierRegisterRequest request) {
        // Check for existing tailor profiles with the same email or phone number
        if (tailorProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered for a tailor profile!");
        }
        if (tailorProfileRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered for a tailor profile!");
        }


        TailorProfile tailorProfile = new TailorProfile();
        tailorProfile.setUsername(request.getUsername());
        tailorProfile.setPassword(request.getPassword());
        tailorProfile.setName(request.getName());
        tailorProfile.setEmail(request.getEmail());
        tailorProfile.setPhoneNumber(request.getPhoneNumber());
        tailorProfile.setShopName(request.getSupplierShopName());
        tailorProfile.setBusinessStartedDate(request.getBusinessStartedDate());
        tailorProfile.setBio(request.getBio());
        tailorProfile.setLocation(request.getLocation());
        // If storing passwords directly in this service, hash it:
        // tailorProfile.setPasswordHash(passwordEncoder.encode(request.getPassword()));

         tailorProfileRepository.save(tailorProfile);
    }

    public TailorProfile updateTailor(Long id, TailorUpdateRequest request, MultipartFile imageFile, List<MultipartFile> worksFiles) throws IOException {
        TailorProfile tailor = tailorProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tailor not found with id: " + id));

        // Update fields
        tailor.setName(request.getName());
        tailor.setEmail(request.getEmail());
        tailor.setPhoneNumber(request.getPhoneNumber());
        tailor.setShopName(request.getShopName());
        tailor.setExperienceYears(request.getExperienceYears());
        tailor.setBio(request.getBio());
        tailor.setLocation(request.getLocation());


        // Image upload handling
        if (imageFile != null && !imageFile.isEmpty()) {
            String sanitized = imageFile.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String fileName = UUID.randomUUID() + "_" + sanitized;
            Path imagePath = Paths.get("uploads/profile_images", fileName);
            Files.createDirectories(imagePath.getParent());
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            tailor.setProfileImageUrl("/images/profile_images/" + fileName); // ✅ Relative path saved to DB
        }

        if (worksFiles != null && !worksFiles.isEmpty()) {
            List<String> savedWorkPaths = new ArrayList<>();

            for (MultipartFile workFile : worksFiles) {
                if (!workFile.isEmpty()) {
                    String sanitized = Objects.requireNonNull(workFile.getOriginalFilename())
                            .replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                    String fileName   = UUID.randomUUID() + "_" + sanitized;

                    Path workPath = Paths.get("uploads/works", fileName);
                    Files.createDirectories(workPath.getParent());
                    Files.copy(workFile.getInputStream(), workPath,
                            StandardCopyOption.REPLACE_EXISTING);

                    savedWorkPaths.add("/images/works/" + fileName);
                }
            }

            // **one‑liner: Tailor now handles JSON→byte[] conversion internally**
            tailor.setWorks(savedWorkPaths);
        }



        return tailorProfileRepository.save(tailor);
    }


    public TailorProfile getTailorById(Long id) {
        return tailorProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tailor not found with id: " + id));
    }

    public List<TailorProfile> getTailorsByRole(String role) {
        return tailorProfileRepository.findByRole(role);
    }

}