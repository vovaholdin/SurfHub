package com.goldin.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CartItemRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List findByUserId(Long user_id) {
        return em.createQuery("SELECT c FROM CartItem c WHERE c.user_id = :user_id")
                .setParameter("user_id", user_id)
                .getResultList();
    }

    @Transactional
    public List findBySurfId(Long surf_id) {
        return em.createQuery("SELECT c from CartItem c WHERE c.surf_id = :surf_id")
                .setParameter("surf_id", surf_id)
                .getResultList();
    }


}
