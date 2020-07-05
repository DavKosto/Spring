package com.geekbrains.spring.mvc.repositories.specifications;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> costGEThan(int minScore) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minScore);
    }

    public static Specification<Product> costLEThan(int maxScore) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxScore);
    }
}
