package org.blueliner.clientservice.dto;

import lombok.Builder;

@Builder
public record OrderRequest (
    Long clientId,
    String description
) {
}
