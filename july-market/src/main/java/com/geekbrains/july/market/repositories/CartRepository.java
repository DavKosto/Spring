package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void deleteProduct(Long id){
        for (Product product : products) {
            if (product.getId().equals(id)){
                products.remove(product);
                return;
            }
        }
    }

    public List<Product> getProducts(){
        return products;
    }
}
