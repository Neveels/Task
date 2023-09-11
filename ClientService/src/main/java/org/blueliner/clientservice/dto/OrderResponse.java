package org.blueliner.clientservice.dto;

import lombok.Builder;

@Builder
public record OrderResponse(
    Long orderId,
    Long clientId,
    String orderStatus,
    String description
) {
}
