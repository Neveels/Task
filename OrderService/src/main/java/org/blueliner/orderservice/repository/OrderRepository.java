package org.blueliner.orderservice.repository;

import org.blueliner.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderByClientIdAndId(Long clientId, Long orderId);

}
