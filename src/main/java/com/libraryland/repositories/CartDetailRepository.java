package com.libraryland.repositories;

import com.libraryland.entities.CartDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends BaseRepository<CartDetail,Long> {
}
