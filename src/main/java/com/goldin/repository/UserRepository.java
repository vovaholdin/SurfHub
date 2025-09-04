package com.goldin.repository;

import com.goldin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    public Optional<User> save(User user) {
        em.persist(user);
        return Optional.of(user);
    }
    @Transactional
    public Optional<User> findById(Long id) {
        return Optional.of(em.find(User.class, id));
    }
    @Transactional
    public Stream<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList().stream();
    }
    @Transactional
    public void delete(Long id) {
        findById(id).ifPresent(em::remove);
    }
    @Transactional
    public Optional<User> update(User user) {
        return Optional.of(em.merge(user));
    }


}
