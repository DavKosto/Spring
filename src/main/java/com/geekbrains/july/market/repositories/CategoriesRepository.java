package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoriesRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
}
