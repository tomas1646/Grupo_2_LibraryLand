package com.libraryland.services;

import com.libraryland.entities.Order;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository) {
        super(baseRepository);
    }
}
