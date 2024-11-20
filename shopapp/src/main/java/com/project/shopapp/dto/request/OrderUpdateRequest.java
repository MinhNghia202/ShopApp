package com.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    String email;
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    String note;
    @JsonProperty("shipping_address")
    String shippingAddress;
}
