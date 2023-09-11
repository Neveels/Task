package org.blueliner.orderservice.model.enums;

import org.blueliner.orderservice.exception.type.InvalidStatusException;

public enum OrderStatus {
    OPENED,
    IN_PROGRESS,
    DELIVERED;

    private static final String INVALID_STATUS = "Invalid status";

    public static OrderStatus isValidStatus(String status) {
        try {
            return OrderStatus.valueOf(status);
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException(INVALID_STATUS);
        }
    }

}
