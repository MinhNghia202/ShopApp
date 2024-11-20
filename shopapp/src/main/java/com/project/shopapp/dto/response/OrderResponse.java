package com.project.shopapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    long id;
    @JsonProperty("user_id")
    long userId;
    @JsonProperty("full_name")
    String fullName;
    String email;
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    String note;

    @JsonProperty("order_date")
    Date orderDate;
    String status;

    @JsonProperty("total_money")
    float totalMoney;

    @JsonProperty("shipping_method")
    String shippingMethod;

    @JsonProperty("shipping_address")
    String shippingAddress;

    @JsonProperty("shipping_date")
    java.sql.Date shippingDate;

    @JsonProperty("tracking_number")
    String trackingNumber;

    @JsonProperty("payment_method")
    String paymentMethod;
    boolean active;
}
