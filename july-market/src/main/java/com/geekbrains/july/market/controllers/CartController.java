package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.CartService;
import com.geekbrains.july.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private ProductsService productsService;

    @Autowired
    public CartController(CartService cartService, ProductsService productsService) {
        this.cartService = cartService;
        this.productsService = productsService;
    }

    @GetMapping
    public String showCart(Model model){
        model.addAttribute("products", cartService.getProducts());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id){
        cartService.addProduct(productsService.findById(id));
        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable Long id){
        cartService.deleteProduct(id);
        return "redirect:/cart/";
    }
}
