package dev.decagon.blog.controller;

import dev.decagon.blog.payload.OrderRequest;
import dev.decagon.blog.payload.OrderResponse;
import dev.decagon.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.placeOrder(request));
    }
}
