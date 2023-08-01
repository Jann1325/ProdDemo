package com.aic.proddemo.web.controller;

import com.aic.proddemo.domain.Cart;
import com.aic.proddemo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/prod/Prod")
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public Cart saveCart(@RequestBody Cart cart){
        return this.cartService.saveCart(cart);
    }
}
