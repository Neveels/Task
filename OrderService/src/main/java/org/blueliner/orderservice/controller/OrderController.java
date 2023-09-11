package org.blueliner.orderservice.controller;

import org.blueliner.orderservice.dto.OrderRequest;
import org.blueliner.orderservice.dto.OrderResponse;
import org.blueliner.orderservice.dto.UpdateOrderStatusRequest;
import org.blueliner.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId,
                                                  @RequestHeader Long clientId) {
        return ResponseEntity.ok(orderService.getOrder(clientId, orderId));
    }

    @PutMapping("/status")
    public ResponseEntity<OrderResponse> changeOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        return ResponseEntity.ok(orderService.changeOrderStatus(updateOrderStatusRequest));
    }

}
