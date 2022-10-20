package com.libraryland.repositories;

import com.libraryland.entities.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends BaseRepository<CartDetail, Long> {
    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    List<CartDetail> search(String filtro);

    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    Page<CartDetail> search(String filtro, Pageable pageable);
}
