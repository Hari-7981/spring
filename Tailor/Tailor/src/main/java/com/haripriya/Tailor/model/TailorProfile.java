package com.haripriya.Tailor.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "total_profiles") // Links to the database table
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
public class TailorProfile {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String name; // Corresponds to 'name' in your request

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username; // Corresponds to 'name' in your request

    @Column(nullable = false, unique = true) // Email must be unique
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true) // mobile
    private String phoneNumber;

    @Column(name = "shop_name") // shopName
    private String shopName;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(columnDefinition = "TEXT") // Allows for longer text
    private String bio; // bio

    @Column(name = "location")
    private String location; // location

    @Column(name = "created_at")
    private LocalDateTime businessStartedDate; // business started date

    @PrePersist
    protected void onCreate() {
        this.businessStartedDate = LocalDateTime.now();
    }


    private String profileImageUrl;


    @Lob
    @Column(name = "works")
    private byte[] works;


    @Transient
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void setWorks(List<String> list) {
        try {
            this.works = (list == null ? null : MAPPER.writeValueAsBytes(list));
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Cannot serialize works list", ex);
        }
    }

    public List<String> getWorks() {
        if (works == null || works.length == 0) return Collections.emptyList();

        try {
            // happy path: proper JSON array
            return MAPPER.readValue(works, new TypeReference<>() {});
        } catch (IOException badJson) {
            // Fallback ❶: treat bytes as UTF‑8 string
            String raw = new String(works, StandardCharsets.UTF_8).trim();
            if (raw.isBlank()) return Collections.emptyList();

            // Fallback ❷: single path or comma‑separated paths
            return Arrays.stream(raw.split("\\s*,\\s*"))
                    .filter(s -> !s.isBlank())
                    .toList();
        }
    }


}