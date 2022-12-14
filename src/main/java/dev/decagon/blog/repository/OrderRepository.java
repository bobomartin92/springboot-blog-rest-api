package dev.decagon.blog.repository;

import dev.decagon.blog.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
