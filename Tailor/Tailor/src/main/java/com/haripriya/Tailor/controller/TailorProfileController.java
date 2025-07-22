package com.haripriya.Tailor.controller;

import com.haripriya.Tailor.Repository.total.TotalProfileRepository;
import com.haripriya.Tailor.dto.*;
import com.haripriya.Tailor.model.TailorProfile;
import com.haripriya.Tailor.service.TailorProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/auth") // Base path for all tailor-related endpoints in this service
@CrossOrigin(origins = "${cors.allowed-origins}", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class TailorProfileController {

    @Autowired
    private TailorProfileService tailorProfileService;

    @Autowired
    private TotalProfileRepository tailorProfileRepository;

    /**
     * Handles the POST request for tailor registration.
     * Expects a JSON request body conforming to TailorRegisterRequest DTO.
     */
    @PostMapping("/register/tailor")
    public ResponseEntity<?> registerTailor(@Valid @RequestBody TailorRegisterRequest registerRequest) {
        try {
            Long userId = tailorProfileService.registerTailor(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId); // 201 Created on success
        } catch (RuntimeException e) {
            // Catch custom exceptions for business logic errors (e.g., duplicate email)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
        } catch (Exception e) {
            // Catch any other unexpected errors
            return new ResponseEntity<>("Tailor registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("/images/profile_images/{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws MalformedURLException {
        Path path = Paths.get("uploads/profile_images").resolve(filename);
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }


    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest registerRequest) {
        try {
            Long userId = tailorProfileService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId); // 201 Created on success
        } catch (RuntimeException e) {
            // Catch custom exceptions for business logic errors (e.g., duplicate email)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
        } catch (Exception e) {
            // Catch any other unexpected errors
            return new ResponseEntity<>("User registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }


    @PostMapping("/register/material")
    public ResponseEntity<?> registerMaterial(@Valid @RequestBody MaterialSupplierRegisterRequest registerRequest) {
        try {
           tailorProfileService.registerMaterial(registerRequest);
            return new ResponseEntity<>(HttpStatus.CREATED); // 201 Created on success
        } catch (RuntimeException e) {
            // Catch custom exceptions for business logic errors (e.g., duplicate email)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
        } catch (Exception e) {
            // Catch any other unexpected errors
            return new ResponseEntity<>("Material Supplier registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    @GetMapping("/tailor/profile/{id}")
    public ResponseEntity<?> getTailor(@PathVariable Long id) {
        try {
            TailorProfile profile = tailorProfileService.getTailorById(id);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch tailor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PostMapping("/user/login")
//    public ResponseEntity<?> getUserIdByCredentials(@RequestBody UserLoginRequest loginRequest) {
//        try {
//            TailorProfile profile = tailorProfileRepository
//                    .findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
//                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));
//            return ResponseEntity.ok(profile.getId());
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PutMapping(value = "/tailor/profile/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateTailor(
            @RequestPart("profile") @Valid TailorUpdateRequest registerRequest,
            @PathVariable Long id,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            @RequestPart(value = "works", required = false) List<MultipartFile> worksFiles)
    {
        try {
            TailorProfile profile = tailorProfileService.updateTailor(id, registerRequest, imageFile, worksFiles);

            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Tailor update failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tailorlist")
    public ResponseEntity<?> getTailorsByRole(@RequestParam(required = false) String role) {
        try {
            List<TailorProfile> profiles = List.of();

            if (role != null && !role.isEmpty()) {
                profiles = tailorProfileService.getTailorsByRole(role);
            } 

            return ResponseEntity.ok(profiles);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch tailor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}