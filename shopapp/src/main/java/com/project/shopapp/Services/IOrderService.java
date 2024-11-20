package com.project.shopapp.Services;

import com.project.shopapp.dto.request.OrderCreationRequest;
import com.project.shopapp.dto.request.OrderUpdateRequest;
import com.project.shopapp.dto.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderCreationRequest request);
    OrderResponse getOrderById(long id);
    OrderResponse updateOrder(long id, OrderUpdateRequest request);
    void deleteOrder(long id);
    List<OrderResponse> getAllOrdersWithUser(long userId);
}
