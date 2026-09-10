package com.java.jwt.enity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    private Instant expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}