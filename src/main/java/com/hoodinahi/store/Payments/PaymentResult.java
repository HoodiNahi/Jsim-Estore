package com.hoodinahi.store.Payments;

import org.springframework.stereotype.Service;

import com.hoodinahi.store.orders.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private Long orderId;
    private OrderStatus paymentStatus;
}
