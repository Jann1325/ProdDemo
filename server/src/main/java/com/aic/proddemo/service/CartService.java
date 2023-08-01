package com.aic.proddemo.service;

import com.aic.proddemo.domain.Cart;
import com.aic.proddemo.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public Cart saveCart(Cart cart){
        return this.cartRepository.save(cart);
    };
}
