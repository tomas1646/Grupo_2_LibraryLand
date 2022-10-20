package com.libraryland.repositories;

import com.libraryland.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends BaseRepository<Cart, Long> {
    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    List<Cart> search(String filtro);

    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    Page<Cart> search(String filtro, Pageable pageable);
}
