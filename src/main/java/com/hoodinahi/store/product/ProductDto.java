package com.hoodinahi.store.product;


import lombok.Data;

@Data
public class ProductDto {
    
    private Long id;
    private String name;
    private String description;
    private double price;
    private Byte categoryId;
}
