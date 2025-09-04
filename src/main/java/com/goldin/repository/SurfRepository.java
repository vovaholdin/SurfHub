package com.goldin.repository;

import com.goldin.entity.Surf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class SurfRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<Surf> save(Surf surf) {
       em.persist(surf);
       return Optional.of(surf);
    }
    @Transactional
    public Optional<Surf> findById(Long id) {
        return Optional.of(em.find(Surf.class, id));
    }
    @Transactional
    public Stream<Surf> findAll() {
        return em.createQuery("SELECT s FROM Surf s", Surf.class).getResultList().stream();
    }
    @Transactional
    public void delete(Long id) {
        findById(id).ifPresent(surf -> em.remove(surf));
    }
    @Transactional
    public Optional<Surf> update(Surf surf) {
        return Optional.of(em.merge(surf));
    }
}
