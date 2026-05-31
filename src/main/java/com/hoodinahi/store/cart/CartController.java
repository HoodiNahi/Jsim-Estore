package com.hoodinahi.store.cart;


import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hoodinahi.store.exceptions.CartNotfoundException;
import com.hoodinahi.store.exceptions.ProductNotfoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "carts", description = "Endpoints for managing shopping carts")
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;
    
    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriComponentsBuilder){

        var cartDto = cartService.createCart();
        var uri = uriComponentsBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "Add an item to the cart", description = "Adds a product to the specified cart")
    public ResponseEntity<CartItemDto> addToCart(
        @Parameter(description = "ID of the cart to which the item will be added", required = true)
        @PathVariable UUID cartId,
        @RequestBody AddItemToCartRequest request
    ){
        var cartItemDto = cartService.addTocart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCartById(@PathVariable @NonNull UUID cartId){

        return cartService.getCartByid(cartId);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateItem(
        @PathVariable("cartId") UUID cartId,
        @PathVariable("productId") Long productId,
        @Valid @RequestBody UpdateCartItemRequest request
    ){
       return cartService.updateItem(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
        @PathVariable("cartId") UUID cartId,
        @PathVariable("productId") Long productId
    ){
        cartService.removeItem(cartId, productId);
         return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(
        @PathVariable("cartId") UUID cartId
    ){
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotfoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotfoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Cart Not found")
        );
    }

    @ExceptionHandler(ProductNotfoundException.class)
     public ResponseEntity<Map<String, String>> handleProductNotFoundException( ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("error", "Product Not found in the Cart")
        );
    }


}
