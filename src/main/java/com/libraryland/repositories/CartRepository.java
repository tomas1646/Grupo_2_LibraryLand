package com.libraryland.repositories;

import com.libraryland.entities.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<Cart,Long> {
}
