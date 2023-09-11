package org.blueliner.orderservice.service.impl;

import org.blueliner.orderservice.dto.OrderRequest;
import org.blueliner.orderservice.dto.OrderResponse;
import org.blueliner.orderservice.exception.type.OrderNotFoundException;
import org.blueliner.orderservice.mapper.OrderMapper;
import org.blueliner.orderservice.model.Order;
import org.blueliner.orderservice.model.enums.OrderStatus;
import org.blueliner.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = OrderServiceImplTest.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    private static final String DEFAULT_STRING = "smth";
    private static final Long orderId = 1L;
    private static final Long clientId = 1L;
    private static Order order;
    private static OrderRequest createOrderRequest;
    private static OrderResponse orderResponse;

    @BeforeAll
    static void setUp() {
        order = Order.builder()
                .orderStatus(OrderStatus.OPENED)
                .id(orderId)
                .clientId(clientId)
                .description(DEFAULT_STRING)
                .build();
        createOrderRequest = OrderRequest.builder()
                .clientId(clientId)
                .description(DEFAULT_STRING)
                .build();
        orderResponse = OrderResponse.builder()
                .orderStatus(OrderStatus.OPENED)
                .orderId(orderId)
                .clientId(clientId)
                .description(DEFAULT_STRING)
                .build();
    }

    @Test
    void createOrder() {
        when(orderMapper.toOrder(createOrderRequest)).thenReturn(order);
        when(orderMapper.toOrderResponse(any())).thenReturn(orderResponse);
        OrderResponse finalOrder = orderService.createOrder(createOrderRequest);
        assertEquals(orderResponse, finalOrder);
    }

    @Test
    void getOrder() {
        when(orderRepository.findOrderByClientIdAndId(any(), any())).thenReturn(Optional.of(order));
        when(orderMapper.toOrderResponse(any())).thenReturn(orderResponse);
        OrderResponse finalOrder = orderService.getOrder(clientId, orderId);
        assertEquals(orderResponse, finalOrder);
    }

    @Test
    void getOrderNegative() {
        when(orderRepository.findOrderByClientIdAndId(any(), any())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrder(clientId,orderId));
    }

}