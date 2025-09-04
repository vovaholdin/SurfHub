package com.goldin.repository;

import com.goldin.entity.Surf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SurfRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Surf surf) {
        em.persist(surf);
    }
    @Transactional
    public Surf findById(Long id) {
        return em.find(Surf.class, id);
    }
    @Transactional
    public List<Surf> findAll() {
        return em.createQuery("SELECT s FROM Surf s", Surf.class).getResultList();
    }
    @Transactional
    public void delete(Surf surf) {
        em.remove(surf);
    }
    @Transactional
    public void update(Surf surf) {
        em.merge(surf);
    }
}
