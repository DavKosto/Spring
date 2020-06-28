package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    List<Product> products;
    Long maxId;

    @PostConstruct
    public void init(){
        products = new ArrayList<>();
        maxId = 0L;
    }

    public void saveOrUpdateProduct(Product product){
        if (product.getId() == null){
            product.setId(++maxId);
            products.add(product);
            return;
        }else {
            for(int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())){
                    products.set(i, product);
                    return;
                }
            }
        }
        throw new  RuntimeException("In the case?");
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        for (Product product : products) {
            if (product.getId().equals(id)){
                return product;
            }
        }
        throw new RuntimeException("Student not found");
    }
}
