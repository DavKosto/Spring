package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
//    List<Product> finAllByCostGreaterThan(int minCost);
//    List<Product> finAllByCostLessThan(int maxCost);
//    List<Product> finAllByCostGreaterThanAndLessThan(int minCost, int maxCost);
}
