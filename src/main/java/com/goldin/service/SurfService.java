package com.goldin.service;

import com.goldin.entity.Surf;
import com.goldin.mapper.SurfMapperTo;
import com.goldin.mapper.dto.SurfTo;
import com.goldin.repository.SurfRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurfService {
    private static final Logger logger = LoggerFactory.getLogger(SurfService.class);
    //save, findbyid, findall, delete, update
    @Autowired
    private SurfRepository surfRepository;
    @Autowired
    private SurfMapperTo mapper;
    @PersistenceContext
    private EntityManager em;

    public List<SurfTo> findAll() {
        return surfRepository.findAll()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public SurfTo findById(Long id) {
        logger.info("findById");
        return surfRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public SurfTo save(Surf surf) {
        return surfRepository.save(surf)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public SurfTo update(Surf surf) {
        return surfRepository.update(surf)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public void delete(Long id) {
        surfRepository.delete(id);
    }

    public SurfTo findByName(String type_surf) {
        return mapper.toDto(em.createQuery("SELECT s FROM Surf s WHERE s.typeSurf = :name", Surf.class)
                .setParameter("name", type_surf)
                .getSingleResult());
    }


}
