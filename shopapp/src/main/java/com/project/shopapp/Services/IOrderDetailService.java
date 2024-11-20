package com.project.shopapp.Services;

import com.project.shopapp.dto.request.OrderDetailCreationRequest;
import com.project.shopapp.dto.request.OrderDetailUpdateRequest;
import com.project.shopapp.dto.response.OrderDetailResponse;
import com.project.shopapp.dto.response.OrderResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request);
    OrderDetailResponse getOrderDetailById(long id);
    OrderDetailResponse updateOrderDetail(long id, OrderDetailUpdateRequest request);
    void deleteOrderDetail(long id);
    List<OrderDetailResponse> getAllOrderDetail();
    List<OrderDetailResponse> getOrderDetailWithOrderId(long orderId);
}
