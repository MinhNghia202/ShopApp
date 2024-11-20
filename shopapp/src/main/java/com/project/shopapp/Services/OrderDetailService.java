package com.project.shopapp.Services;

import com.project.shopapp.Repositories.OrderDetailRepository;
import com.project.shopapp.Repositories.OrderRepository;
import com.project.shopapp.Repositories.ProductRepository;
import com.project.shopapp.dto.request.OrderDetailCreationRequest;
import com.project.shopapp.dto.request.OrderDetailUpdateRequest;
import com.project.shopapp.dto.response.OrderDetailResponse;
import com.project.shopapp.exception.AppException;
import com.project.shopapp.exception.ErrorCode;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    private static final Logger log = LoggerFactory.getLogger(OrderDetailService.class);
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    private Order getOrderById(long id){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities()
                .forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return orderRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND_EXCEPTION));
    }
    private Product getProductById(long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION));
    }

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request) {
        Order order = getOrderById(request.getOrderId());
        Product product = getProductById(request.getProductId());

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(request.getPrice())
                .numberOfProduct(request.getNumberOfProduct())
                .totalMoney(request.getTotalMoney())
                .color(request.getColor())
                .build();
        orderDetailRepository.save(orderDetail);
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .order(order)
                .product(product)
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .color(orderDetail.getColor())
                .build();
    }

    private OrderDetail getById(long id){
        return orderDetailRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND_EXCEPTION));
    }

    @Override
    public OrderDetailResponse getOrderDetailById(long id) {
        OrderDetail orderDetail = getById(id);
        Order order = getOrderById(orderDetail.getOrder().getId());
        Product product = getProductById(orderDetail.getProduct().getId());
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .order(order)
                .product(product)
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .color(orderDetail.getColor())
                .build();
    }

    @Override
    public OrderDetailResponse updateOrderDetail(long id, OrderDetailUpdateRequest request) {
        OrderDetail orderDetail = getById(id);
        orderDetail.setNumberOfProduct(request.getNumberOfProduct());
        orderDetail.setTotalMoney(request.getTotalMoney());
        orderDetail.setColor(request.getColor());
        orderDetailRepository.save(orderDetail);
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .order(getOrderById(orderDetail.getOrder().getId()))
                .product(getProductById(orderDetail.getProduct().getId()))
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .color(orderDetail.getColor())
                .build();
    }

    @Override
    public void deleteOrderDetail(long id) {
        OrderDetail orderDetail = getById(id);
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetail() {
        return orderDetailRepository.findAll().stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getId())
                        .order(getOrderById(orderDetail.getOrder().getId()))
                        .product(getProductById(orderDetail.getProduct().getId()))
                        .price(orderDetail.getPrice())
                        .numberOfProduct(orderDetail.getNumberOfProduct())
                        .totalMoney(orderDetail.getTotalMoney())
                        .color(orderDetail.getColor())
                        .build()).toList();
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailWithOrderId(long orderId) {
        return orderDetailRepository.findAllByOrderId(orderId).stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getId())
                        .order(getOrderById(orderDetail.getOrder().getId()))
                        .product(getProductById(orderDetail.getProduct().getId()))
                        .price(orderDetail.getPrice())
                        .numberOfProduct(orderDetail.getNumberOfProduct())
                        .totalMoney(orderDetail.getTotalMoney())
                        .color(orderDetail.getColor())
                        .build()).toList();
    }
}
