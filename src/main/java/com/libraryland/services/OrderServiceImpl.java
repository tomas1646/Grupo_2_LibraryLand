package com.libraryland.services;

import com.libraryland.entities.Order;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, EntityManager entityManager) {
        super(baseRepository, entityManager);
    }

    @Override
    @Transactional
    public Order save(Order order) throws Exception {
        try {
            order.addOrderDetails(order.getDetails());
            order = baseRepository.save(order);
            baseRepository.flush();
            refresh(order);
            return order;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
