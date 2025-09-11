package com.goldin.service;

import com.goldin.entity.CartItem;
import com.goldin.entity.Surf;
import com.goldin.entity.User;
import com.goldin.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addToCart(User user, Surf surf, int quantity) {
        return cartItemRepository.addToCart(user, surf, quantity);
    }

    public void removeFromCart(User user, Surf surf, int quantity) {
        cartItemRepository.removeFromCart(user, surf, quantity);
    }
}
