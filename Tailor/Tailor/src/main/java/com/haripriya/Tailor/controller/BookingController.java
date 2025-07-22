package com.haripriya.Tailor.controller;

import com.haripriya.Tailor.Repository.booking.BookingRepository;
import com.haripriya.Tailor.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "${cors.allowed-origins}", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class BookingController {


    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAllbookings() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking createbooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        System.out.println("Fetching bookings for user ID: " + userId);
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updatebooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStatus(bookingDetails.getStatus());
            booking.setDeliveryDate(bookingDetails.getDeliveryDate());
            booking.setCost(bookingDetails.getCost());
            booking.setDescription(bookingDetails.getDescription());
            return ResponseEntity.ok(bookingRepository.save(booking));
        }).orElse(ResponseEntity.notFound().build());
    }


}



