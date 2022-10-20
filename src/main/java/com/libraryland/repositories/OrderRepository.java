package com.libraryland.repositories;

import com.libraryland.entities.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
    @Query(value = "SELECT p FROM Order p WHERE p.number = cast(:filtro as int) OR p.total = cast(:filtro as int)")
    List<Order> search(@Param("filtro") String filtro);

    @Query(value = "SELECT p FROM Order p WHERE p.number = cast(:filtro as int) OR p.total = cast(:filtro as int)")
    Page<Order> search(@Param("filtro") String filtro, Pageable pageable);
}
