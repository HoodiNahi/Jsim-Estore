package com.hoodinahi.store.Payments;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutRequest {

    @NotNull(message = "cart id is required")
    private UUID cartId;
}
