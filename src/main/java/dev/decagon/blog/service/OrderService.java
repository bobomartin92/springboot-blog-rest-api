package dev.decagon.blog.service;

import dev.decagon.blog.payload.OrderRequest;
import dev.decagon.blog.payload.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
}
