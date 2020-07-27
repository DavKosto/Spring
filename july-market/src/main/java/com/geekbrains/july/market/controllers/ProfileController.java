package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Role;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showProfile(Principal principal, Model model){
        User user = usersService.findOneUserByPhone(principal.getName());
        List<User> users = usersService.findAllBy();
        Role role = usersService.getAccess(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("role", role);
        return "profile_page";
    }

    @GetMapping("/changeAccess/{id}")
    public void changeAccess(@PathVariable Long id, @RequestParam String access, HttpServletRequest request, HttpServletResponse response) throws IOException {
        usersService.changeAccess(id, access);
        response.sendRedirect(request.getHeader("referer"));
    }

}
