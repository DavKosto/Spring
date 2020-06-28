package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository products;

    @Autowired
    public ProductService(ProductRepository products){
        this.products = products;
    }

    public void saveOrUpdateProduct(Product product){
        products.saveOrUpdateProduct(product);
    }

    public List<Product> findAll(){
        return products.findAll();
    }

    public Product findById(Long id){
        return products.findById(id);
    }
}
