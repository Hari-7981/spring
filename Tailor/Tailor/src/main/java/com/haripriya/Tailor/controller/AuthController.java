package com.haripriya.Tailor.controller;

import com.haripriya.Tailor.Repository.total.TotalProfileRepository;
import com.haripriya.Tailor.config.JwtTokenProvider;
import com.haripriya.Tailor.dto.AuthResponse;
import com.haripriya.Tailor.dto.UserLoginRequest;
import com.haripriya.Tailor.model.TailorProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

// AuthController.java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private TotalProfileRepository tailorProfileRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TailorProfile user = tailorProfileRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole("user");
       // Ensure your entity has a role field

        return ResponseEntity.ok(response);
    }
}