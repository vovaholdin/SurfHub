package com.goldin.service;

import com.goldin.entity.CartItem;
import com.goldin.entity.Surf;
import com.goldin.entity.User;
import com.goldin.mapper.SurfMapperTo;
import com.goldin.mapper.dto.SurfTo;
import com.goldin.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private SurfMapperTo surfMapperTo;

    public CartItem addToCart(User user, SurfTo surf, int quantity) {

        return cartItemRepository.addToCart(user, surfMapperTo.toEntity(surf), quantity);
    }

    public void removeFromCart(User user, Surf surf, int quantity) {
        cartItemRepository.removeFromCart(user, surf, quantity);
    }
}
