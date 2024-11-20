package com.project.shopapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    @JsonProperty("order_id")
    long orderId;
    @JsonProperty("product_id")
    long productId;
    float price;
    @JsonProperty("number_of_product")
    int numberOfProduct;
    @JsonProperty("total_money")
    float totalMoney;
    String color;
}
