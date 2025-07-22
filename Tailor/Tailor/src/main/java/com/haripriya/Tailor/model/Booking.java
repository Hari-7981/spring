package com.haripriya.Tailor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings") // Links to the database table
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name= "status")
    private String status;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
