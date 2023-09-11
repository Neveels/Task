package org.blueliner.clientservice.dto;

import lombok.Builder;

@Builder
public record UpdateOrderStatusRequest(
    Long orderId,
    String orderStatus
) {
}
