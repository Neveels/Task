package org.blueliner.clientservice.controller;

import jakarta.validation.Valid;
import org.blueliner.clientservice.dto.CreateOrderRequest;
import org.blueliner.clientservice.dto.OrderResponse;
import org.blueliner.clientservice.dto.UpdateOrderStatusRequest;
import org.blueliner.clientservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return clientService.createOrder(createOrderRequest);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId,
                                                  @RequestHeader Long clientId) {
        return clientService.getOrder(clientId, orderId);
    }

    @PutMapping("/status")
    public ResponseEntity<OrderResponse> changeOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        return clientService.changeOrderStatus(updateOrderStatusRequest);
    }

}
