CREATE TABLE orders(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL ,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    constraint orders_user_id_fk
        Foreign Key (customer_id) REFERENCES users(id)
);

CREATE TABLE order_items(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    constraint order_items_order_id_fk
        FOREIGN KEY (order_id) REFERENCES orders(id),
    constraint order_items_product_id_fk
        FOREIGN KEY (product_id) REFERENCES products(id)
);