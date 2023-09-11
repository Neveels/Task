package org.blueliner.orderservice.service.impl;

import org.blueliner.orderservice.dto.*;
import org.blueliner.orderservice.exception.type.InvalidStatusException;
import org.blueliner.orderservice.exception.type.OrderNotFoundException;
import org.blueliner.orderservice.mapper.OrderMapper;
import org.blueliner.orderservice.model.enums.OrderStatus;
import org.blueliner.orderservice.model.Order;
import org.blueliner.orderservice.repository.OrderRepository;
import org.blueliner.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_NOT_FOUND_EXCEPTION = "Order not found";
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse getOrder(Long clientId, Long orderId) {
        Order order = orderRepository.findOrderByClientIdAndId(clientId, orderId)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_EXCEPTION));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse changeOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        String status = updateOrderStatusRequest.orderStatus();
        OrderStatus validStatus = OrderStatus.isValidStatus(status);
        return orderMapper.toOrderResponse(orderRepository.findById(updateOrderStatusRequest.orderId())
                .map((order) -> {
                    order.setOrderStatus(validStatus);
                    return orderRepository.save(order);
                }).orElseThrow(() ->
                        new OrderNotFoundException(ORDER_NOT_FOUND_EXCEPTION)
                ));
    }

}
