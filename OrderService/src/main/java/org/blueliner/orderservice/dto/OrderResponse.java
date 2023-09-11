package org.blueliner.orderservice.dto;

import lombok.Builder;
import org.blueliner.orderservice.model.enums.OrderStatus;

@Builder
public record OrderResponse(
    Long orderId,
    Long clientId,
    OrderStatus orderStatus,
    String description
) {
}
