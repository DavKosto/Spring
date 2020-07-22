package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addProduct(Product product){
        cartRepository.addProduct(product);
    }

    public void deleteProduct(Long id){
        cartRepository.deleteProduct(id);
    }

    public List<Product> getProducts(){
        return cartRepository.getProducts();
    }
}
