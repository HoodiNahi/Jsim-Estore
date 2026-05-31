package com.hoodinahi.store.orders;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {
    private OrderProductDto product;
    private int qauntity;
    private BigDecimal totalPrice;
}
