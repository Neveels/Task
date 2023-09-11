package org.blueliner.orderservice.mapper;

import lombok.NoArgsConstructor;
import org.blueliner.orderservice.dto.CreateOrderRequest;
import org.blueliner.orderservice.dto.OrderRequest;
import org.blueliner.orderservice.dto.OrderResponse;
import org.blueliner.orderservice.model.enums.OrderStatus;
import org.blueliner.orderservice.model.Order;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
            .orderStatus(OrderStatus.OPENED)
            .description(orderRequest.description())
            .clientId(orderRequest.clientId())
            .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
            .orderId(order.getId())
            .clientId(order.getClientId())
            .description(order.getDescription())
            .orderStatus(order.getOrderStatus())
            .build();
    }

}
