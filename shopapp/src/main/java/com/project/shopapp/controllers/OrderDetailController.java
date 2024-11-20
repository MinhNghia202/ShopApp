package com.project.shopapp.controllers;

import com.project.shopapp.Services.OrderDetailService;
import com.project.shopapp.dto.request.ApiResponse;
import com.project.shopapp.dto.request.OrderDetailCreationRequest;
import com.project.shopapp.dto.request.OrderDetailUpdateRequest;
import com.project.shopapp.dto.response.OrderDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailCreationRequest request){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.createOrderDetail(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> updateOrderDetail(
            @PathVariable("id") long id, @RequestBody OrderDetailUpdateRequest request){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateOrderDetail(id, request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> getOrderDetailById(@PathVariable("id") long id){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.getOrderDetailById(id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrderDetail(@PathVariable("id") long id){
        orderDetailService.deleteOrderDetail(id);
        return ApiResponse.<Void>builder()
                .build();
    }
    @GetMapping("/order/{order_id}")
    public ApiResponse<List<OrderDetailResponse>> getAllOrderDetailWithOrder(@PathVariable("order_id") long order_id){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailWithOrderId(order_id))
                .build();
    }
    @GetMapping
    public ApiResponse<List<OrderDetailResponse>> getAllOrderDetails(){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAllOrderDetail())
                .build();
    }
}
