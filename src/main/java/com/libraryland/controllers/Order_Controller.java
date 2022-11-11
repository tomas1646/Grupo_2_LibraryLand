package com.libraryland.controllers;

import com.libraryland.entities.Order;
import com.libraryland.entities.User;
import com.libraryland.services.OrderServiceImpl;
import com.libraryland.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
public class Order_Controller {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/orders")
    public String getOrders(Authentication authentication, Model model) {
        try {
            String username = authentication.getName();
            Optional<User> currentUser = userService.findByName(username);

            if (currentUser.isEmpty()) {
                throw new Exception("El usuario no se encuentra en la base de datos");
            }

            User user = currentUser.get();
            List<Order> orders = orderService.findByUser(user);
            model.addAttribute("orders", orders);
            return "views/orders";
        } catch (Exception e) {
            return "views/error";
        }
    }
}