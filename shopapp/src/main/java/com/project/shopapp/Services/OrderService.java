package com.project.shopapp.Services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.Repositories.OrderRepository;
import com.project.shopapp.Repositories.UserRepository;
import com.project.shopapp.dto.request.OrderCreationRequest;
import com.project.shopapp.dto.request.OrderUpdateRequest;
import com.project.shopapp.dto.response.OrderResponse;
import com.project.shopapp.exception.AppException;
import com.project.shopapp.exception.ErrorCode;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderStatus;
import com.project.shopapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    private User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
    }
//    @PostAuthorize("returnObject.username == authentication.name")
    @Override
    public OrderResponse createOrder(OrderCreationRequest request) {
        User user = findUserById(request.getUserId());
        Order order = Order.builder()
                .user(user)
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .note(request.getNote())
                .orderDate(new Date())
                .status(OrderStatus.PENDING)
                .totalMoney(request.getTotalMoney())
                .shippingMethod(request.getShippingMethod())
                .shippingAddress(request.getShippingAddress())
                .shippingDate(request.getShippingDate())
                .trackingNumber(request.getTrackingNumber())
                .paymentMethod(request.getPaymentMethod())
                .active(true)
                .build();
        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .fullName(order.getFullName())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .address(order.getAddress())
                .note(order.getNote())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalMoney(order.getTotalMoney())
                .shippingMethod(order.getShippingMethod())
                .shippingAddress(order.getShippingAddress())
                .shippingDate(order.getShippingDate())
                .trackingNumber(order.getTrackingNumber())
                .paymentMethod(order.getPaymentMethod())
                .active(order.isActive())
                .build();
    }

    private Order getById(long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND_EXCEPTION));
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = getById(id);
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .fullName(order.getFullName())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .address(order.getAddress())
                .note(order.getNote())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalMoney(order.getTotalMoney())
                .shippingMethod(order.getShippingMethod())
                .shippingAddress(order.getShippingAddress())
                .shippingDate(order.getShippingDate())
                .trackingNumber(order.getTrackingNumber())
                .paymentMethod(order.getPaymentMethod())
                .active(order.isActive())
                .build();
    }

    @Override
    public OrderResponse updateOrder(long id, OrderUpdateRequest request) {
        Order order = getById(id);
        order.setEmail(request.getEmail());
        order.setPhoneNumber(request.getPhoneNumber());
        order.setAddress(request.getAddress());
        order.setNote(request.getNote());
        order.setShippingAddress(request.getShippingAddress());
        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .fullName(order.getFullName())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .address(order.getAddress())
                .note(order.getNote())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalMoney(order.getTotalMoney())
                .shippingMethod(order.getShippingMethod())
                .shippingAddress(order.getShippingAddress())
                .shippingDate(order.getShippingDate())
                .trackingNumber(order.getTrackingNumber())
                .paymentMethod(order.getPaymentMethod())
                .active(order.isActive())
                .build();
    }

    @Override
    public void deleteOrder(long id) {
        Order order = getById(id);
        if(order!=null){
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<OrderResponse> getAllOrdersWithUser(long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .userId(order.getUser().getId())
                        .fullName(order.getFullName())
                        .email(order.getEmail())
                        .phoneNumber(order.getPhoneNumber())
                        .address(order.getAddress())
                        .note(order.getNote())
                        .orderDate(order.getOrderDate())
                        .status(order.getStatus())
                        .totalMoney(order.getTotalMoney())
                        .shippingMethod(order.getShippingMethod())
                        .shippingAddress(order.getShippingAddress())
                        .shippingDate(order.getShippingDate())
                        .trackingNumber(order.getTrackingNumber())
                        .paymentMethod(order.getPaymentMethod())
                        .active(order.isActive())
                        .build()).toList();
    }
}
