package com.hoodinahi.store.Payments;

import java.util.Optional;

import com.hoodinahi.store.orders.Order;

public interface PaymentGateway {

    CheckoutSessoion createCheckoutSession(Order order);
    Optional<PaymentResult> pareWebhookRequest(WebhookRequest request);
}
