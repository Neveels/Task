package org.blueliner.clientservice.service;


import org.blueliner.clientservice.dto.CreateOrderRequest;
import org.blueliner.clientservice.dto.CreateUserRequest;
import org.blueliner.clientservice.dto.OrderResponse;
import org.blueliner.clientservice.dto.UpdateOrderStatusRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<OrderResponse> createOrder(CreateOrderRequest createOrderRequest);

    ResponseEntity<OrderResponse> getOrder(Long clientId, Long orderId);

    ResponseEntity<OrderResponse> changeOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest);
}
