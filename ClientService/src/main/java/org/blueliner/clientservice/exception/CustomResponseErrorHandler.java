package org.blueliner.clientservice.exception;

import org.blueliner.clientservice.exception.type.InvalidStatusException;
import org.blueliner.clientservice.exception.type.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
    private static final String ORDER_NOT_FOUND = "Order not found";
    private static final String INVALID_STATUS = "Invalid status";


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new OrderNotFoundException(ORDER_NOT_FOUND);
        } else {
            throw new InvalidStatusException(INVALID_STATUS);
        }
    }

}
