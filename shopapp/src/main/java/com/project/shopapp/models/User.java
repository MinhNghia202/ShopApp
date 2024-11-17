package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "fullname" , length = 100)
    String fullName;

    @Column(name = "email" , nullable = false, length = 100)
    String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    String phoneNumber;

    @Column(name = "address", length = 200)
    String address;

    @Column(name = "password", length = 200, nullable = false)
    String password;

    @Column(name = "active")
    boolean active;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "facebook_account_id")
    int facebookAccountId;

    @Column(name = "google_account_id")
    int googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
