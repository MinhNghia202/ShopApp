package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "token", length = 255)
    String token;

    @Column(name = "token_type", length = 50)
    String tokenType;

    @Column(name = "expiration_date")
    LocalDate expirationDate;

    boolean revoked;
    boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
