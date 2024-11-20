package com.project.shopapp.controllers;

import com.project.shopapp.Services.OrderService;
import com.project.shopapp.dto.request.ApiResponse;
import com.project.shopapp.dto.request.OrderCreationRequest;
import com.project.shopapp.dto.request.OrderUpdateRequest;
import com.project.shopapp.dto.response.OrderResponse;
import com.project.shopapp.models.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderCreationRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable("id") long id){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable("id") long id, @RequestBody OrderUpdateRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(id,request))
                .build();
    }

    @GetMapping("/user/{user_id}")
    public ApiResponse<List<OrderResponse>> getAllOrdersWithUser(@PathVariable("user_id") long user_id){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrdersWithUser(user_id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable("id") long id){
        orderService.deleteOrder(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
