package com.goldin.repository;

import com.goldin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public void save(User user) {
        em.persist(user);
    }
    @Transactional
    public User findByUsername(String username) {
        return em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
    @Transactional
    public User findById(Long id) {
        return em.find(User.class, id);
    }
    @Transactional
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }
    @Transactional
    public void update(User user) {
        em.merge(user);
    }

}
