package com.project.shopapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {

    long id;
    Order order;
    Product product;
    float price;

    @JsonProperty("number_of_product")
    int numberOfProduct;
    @JsonProperty("total_money")
    float totalMoney;
    String color;
}
