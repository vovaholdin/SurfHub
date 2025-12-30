package com.goldin.service;

import com.goldin.entity.User;
import com.goldin.mapper.UserMapperTo;
import com.goldin.mapper.dto.UserTo;
import com.goldin.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapperTo mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserTo save(User user) {
        String info = isUserUnique(user);
        if (info.isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user)
                    .map(mapper::toDto)
                    .orElseThrow();
        } else
            throw new IllegalArgumentException("User with same " + info + " already exists");
    }

    public UserTo findById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public Optional<User> findByIdRealUser(Long id) {
        return userRepository.findByIdRealUser(id);
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

    private String isUserUnique(User user){
        List<User> all = userRepository.findAll().toList();
        for(User user1 : all){
            if (user1.getName().equals(user.getName())) {
                return "name";
            }
            if (user1.getEmail().equals(user.getEmail())) {
                return "email";
            }
            if (user1.getPhone().equals(user.getPhone())) {
                return "phone";
            }
        }
        return "";
    }

}
