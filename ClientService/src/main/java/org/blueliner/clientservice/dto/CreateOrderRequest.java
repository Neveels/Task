package org.blueliner.clientservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record CreateOrderRequest(
    @Email(message = "Invalid email address")
    String email,
    String description
) {
}
