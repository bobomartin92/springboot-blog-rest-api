package dev.decagon.blog.payload;

import dev.decagon.blog.entity.Order;
import dev.decagon.blog.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
