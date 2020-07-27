package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private Cart cart;
    private ProductsService productService;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductsService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCart(Model model){
        List<OrderItem> items = cart.getItems();
        BigDecimal price = cart.getPrice();
        model.addAttribute("items", items);
        model.addAttribute("price", price);
        return "cart_page";
    }

    @GetMapping("/remove/{id}")
    public void removeProductOfCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        cart.removeProductById(id);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.addProduct(productService.findById(id));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/clear")
    public void clearCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.clear();
        response.sendRedirect(request.getHeader("referer"));
    }
}
