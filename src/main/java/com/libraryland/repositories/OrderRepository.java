package com.libraryland.repositories;

import com.libraryland.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order,Long> {
}
