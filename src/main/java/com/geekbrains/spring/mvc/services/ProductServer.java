package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServer {
    private ProductRepository  productRepository;

    @Autowired
    public ProductServer(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

//    public List<Product> finAllByCostGreaterThanAndLessThan(int minCost, int maxCost){
//        return productRepository.finAllByCostGreaterThanAndLessThan(minCost, maxCost);
//    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }
}
