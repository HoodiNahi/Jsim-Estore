package com.hoodinahi.store.exceptions;

public class CartNotfoundException extends RuntimeException {
    public CartNotfoundException() {
        super("Cart not found");
    }
}
