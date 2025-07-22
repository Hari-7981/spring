package com.haripriya.Tailor.config;

import com.haripriya.Tailor.Repository.total.TotalProfileRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// com.haripriya.Tailor.service.CustomUserDetailsService (or similar)
@Service // Make sure it's a Spring-managed component
public class CustomUserDetailsService implements UserDetailsService {

    private final TotalProfileRepository totalProfileRepository; // Inject your repository

    public CustomUserDetailsService(TotalProfileRepository totalProfileRepository) {
        this.totalProfileRepository = totalProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return totalProfileRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        // Add authorities based on your user's roles
                        List.of(new SimpleGrantedAuthority("ROLE_USER")) // Example, adjust as needed
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}