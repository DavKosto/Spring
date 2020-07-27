package com.geekbrains.july.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal price;

    public OrderItem(Product product){
        this.product = product;
    }
}
