package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.AnnotatedArrayType;
import java.time.LocalDate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "fullname" , length = 100)
    String fullName;

    @Column(name = "email" , nullable = false, length = 100)
    String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    String phoneNumber;

    @Column(name = "address", length = 200)
    String address;

    @Column(name = "note", length = 100)
    String note;

    @Column(name = "order_date")
    LocalDate orderDate;

    @Column(name = "status")
    String status;

    @Column(name = "total_money")
    float totalMoney;

    @Column(name = "shipping_method")
    String shippingMethod;

    @Column(name = "shipping_address")
    String shippingAddress;

    @Column(name = "shipping_date")
    Data shippingDate;

    @Column(name = "tracking_number")
    String trackingNumber;

    @Column(name = "payment_method")
    String paymentMethod;

    @Column(name = "active")
    boolean active;
}
