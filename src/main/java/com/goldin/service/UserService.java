package com.goldin.service;

import com.goldin.entity.User;
import com.goldin.mapper.UserMapperTo;
import com.goldin.mapper.dto.UserTo;
import com.goldin.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapperTo mapper;

    public UserTo save(User user) {
        return userRepository.save(user)
                .map(mapper::toDto)
                .orElseThrow();

    }

    public UserTo findById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public List<UserTo> findAll() {
        return userRepository.findAll()
                .map(mapper::toDto)
                .toList();
    }

    public UserTo findByName(String name) {
        return mapper.toDto(em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult());

    }

    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    public UserTo update(User user) {
        return userRepository.update(user)
                .map(mapper::toDto)
                .orElseThrow();
    }

}
