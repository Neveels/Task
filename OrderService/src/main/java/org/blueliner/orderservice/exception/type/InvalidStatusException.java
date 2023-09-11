package org.blueliner.orderservice.exception.type;

public class InvalidStatusException extends IllegalArgumentException {
    public InvalidStatusException(String message) {
        super(message);
    }

}
