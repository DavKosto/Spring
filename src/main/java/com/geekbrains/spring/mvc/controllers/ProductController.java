package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.specifications.ProductSpecifications;
import com.geekbrains.spring.mvc.services.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    private ProductServer productServer;

    @Autowired
    public ProductController(ProductServer productServer){
        this.productServer = productServer;
    }

    @GetMapping
    public String show(){
        return "index";
    }

    @GetMapping("/products")
    public String showAllStudents(Model model,
                                  @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_cost", required = false) Integer minCost,
                                  @RequestParam(name = "max_cost", required = false) Integer maxCost
                                  ) {
//        model.addAttribute("products", products.finAllByCostGreaterThanAndLessThan(minCost, maxCost));

        Specification<Product> spec = Specification.where(null);
        if (minCost != null){
            spec = spec.and(ProductSpecifications.costGEThan(minCost));
        }
        if (maxCost != null){
            spec = spec.and(ProductSpecifications.costLEThan(maxCost));
        }

        List<Product> products = productServer.findAll(spec, pageNumber).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }
}
