package com.hoodinahi.store.orders;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.hoodinahi.store.auth.AuthService;
import com.hoodinahi.store.exceptions.OrderNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders(){

        var user = authService.getCurrentUser();
        var orders = orderRepository.gerOrdersByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId)
                    .orElseThrow(OrderNotFoundException::new);
        
        var user = authService.getCurrentUser();
        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You don't have permission to access this order");
        }
        return  orderMapper.toDto(order);
    }
}
