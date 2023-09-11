package org.blueliner.orderservice.service;

import org.blueliner.orderservice.dto.*;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrder(Long clientId, Long orderId);
    OrderResponse changeOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest);

}
