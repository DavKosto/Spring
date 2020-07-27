package com.geekbrains.july.market.beans;

import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private List<OrderItem> items;
    private BigDecimal price;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void clear(){
        items.clear();
        recalculate();
    }

    public void addProduct(Product product){
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())){
                item.setQuantity(item.getQuantity()+1);
                item.setPrice(new BigDecimal(item.getQuantity() * item.getProduct().getPrice()));
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(product));

    }

    public void removeProductById(Long productId){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(productId)){
                items.remove(i);
                recalculate();
            }
        }
    }

    public void recalculate(){
        price = new BigDecimal(0.0);
        for (OrderItem item : items) {
            price = price.add(item.getPrice());
        }
    }
}
