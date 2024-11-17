package com.project.shopapp.Repositories;

import com.project.shopapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
