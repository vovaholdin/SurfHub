package com.goldin.repository;

import com.goldin.entity.CartItem;
import com.goldin.entity.Surf;
import com.goldin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class CartItemRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public CartItem addToCart(User user, Surf surf, int quantity) {
        CartItem existingItem = em.createQuery("SELECT c from CartItem c where c.user = :user and c.surf = :surf", CartItem.class)
                .setParameter("user", user)
                .setParameter("surf", surf)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return em.merge(existingItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setSurf(surf);
            cartItem.setQuantity(quantity);
            em.persist(cartItem);
            return cartItem;
        }
    }

    @Transactional
    public void removeFromCart(User user, Surf surf, int quantity) {
        CartItem cartItem = em.createQuery("SELECT c from CartItem c where c.user = :user and c.surf = :surf", CartItem.class)
                .setParameter("user", user)
                .setParameter("surf", surf)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such CartItem"));
        if(cartItem.getQuantity() <= quantity) {
            em.remove(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            em.merge(cartItem);
        }

    }




}
