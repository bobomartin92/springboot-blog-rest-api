package dev.decagon.blog.service.impl;

import dev.decagon.blog.entity.Order;
import dev.decagon.blog.entity.Payment;
import dev.decagon.blog.exception.BlogAPIException;
import dev.decagon.blog.payload.OrderRequest;
import dev.decagon.blog.payload.OrderResponse;
import dev.decagon.blog.repository.OrderRepository;
import dev.decagon.blog.repository.PaymentRepository;
import dev.decagon.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = request.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(String.valueOf(UUID.randomUUID()));
        orderRepository.save(order);

        Payment payment = request.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Payment Card Type Not Supported");
        }

        order.setStatus("DONE");

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");


        return orderResponse;
    }
}
