package org.blueliner.orderservice.dto;

import lombok.Builder;

@Builder
public record UpdateOrderStatusRequest(
    Long orderId,
    String orderStatus
) {
}
