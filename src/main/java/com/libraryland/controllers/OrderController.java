package com.libraryland.controllers;

import com.libraryland.entities.Order;
import com.libraryland.services.OrderServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/orders")
public class OrderController extends BaseControllerImpl<Order, OrderServiceImpl> {
}
