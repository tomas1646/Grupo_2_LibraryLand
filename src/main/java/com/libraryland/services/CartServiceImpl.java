package com.libraryland.services;

import com.libraryland.entities.Cart;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.CartRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl extends BaseServiceImpl<Cart, Long> implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl(BaseRepository<Cart, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Cart> search(String filtro) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet");
    }

    @Override
    public Page<Cart> search(String filtro, Pageable pageable) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet");
    }

    @Override
    @Transactional
    public Cart save(Cart cart) throws Exception {
        try {
            cart.addCartDetails(cart.getDetails());
            cart.addUser();
            cart = baseRepository.save(cart);
            return cart;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
