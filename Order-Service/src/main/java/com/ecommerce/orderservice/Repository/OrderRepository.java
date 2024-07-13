package com.ecommerce.orderservice.Repository;

import com.ecommerce.orderservice.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
