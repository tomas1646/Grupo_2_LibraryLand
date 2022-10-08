package com.libraryland.services;

import com.libraryland.entities.Cart;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends BaseServiceImpl<Cart, Long> implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl(BaseRepository<Cart, Long> baseRepository) {
        super(baseRepository);
    }
}
