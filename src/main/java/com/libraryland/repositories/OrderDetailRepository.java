package com.libraryland.repositories;

import com.libraryland.entities.Base;
import com.libraryland.entities.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail,Long> {
}
