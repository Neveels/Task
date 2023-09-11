package org.blueliner.orderservice.dto;

import lombok.Builder;

@Builder
public record GetOrderRequest(
    Long clientId,
    Long orderId
) {
}
