package com.hoodinahi.store.Payments;





import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoodinahi.store.auth.AuthService;
import com.hoodinahi.store.cart.CartRepository;
import com.hoodinahi.store.cart.CartService;
import com.hoodinahi.store.exceptions.CartEmptyException;
import com.hoodinahi.store.exceptions.CartNotfoundException;
import com.hoodinahi.store.orders.Order;
import com.hoodinahi.store.orders.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CheckoutService {


    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;



    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
         var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotfoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

       var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
           
        var session =paymentGateway.createCheckoutSession(order);
        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException ex) {
            System.out.println(ex.getMessage());
            orderRepository.delete(order);
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest request){

        paymentGateway.pareWebhookRequest(request)
        .ifPresent(paymentResult -> {
            var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
            order.setStatus(paymentResult.getPaymentStatus());
            orderRepository.save(order);
        });
       
    }

}
