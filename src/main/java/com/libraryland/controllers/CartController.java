package com.libraryland.controllers;


import com.libraryland.entities.Cart;
import com.libraryland.services.CartServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/carts")
public class CartController extends BaseControllerImpl<Cart, CartServiceImpl> {
}
