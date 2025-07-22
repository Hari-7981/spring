package com.haripriya.Tailor.Repository.total;

import com.haripriya.Tailor.model.TailorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TotalProfileRepository extends JpaRepository<TailorProfile, Long> {
    // Custom query methods to check for existing email/phone, ensuring uniqueness
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<TailorProfile> findByUsernameAndPassword(String username, String password);
    Optional<TailorProfile> findByUsername(String username);
    List<TailorProfile> findByRole(String role);

}