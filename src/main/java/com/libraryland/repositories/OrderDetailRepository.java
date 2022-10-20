package com.libraryland.repositories;

import com.libraryland.entities.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail, Long> {
    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    List<OrderDetail> search(String filtro);

    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    Page<OrderDetail> search(String filtro, Pageable pageable);
}
