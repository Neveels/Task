package org.blueliner.orderservice.dto;

import lombok.Builder;

@Builder
public record OrderRequest (
    Long clientId,
    String description
) {
}
