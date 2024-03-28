package com.bosch.example.backenduserapp.services;

import com.bosch.example.backenduserapp.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> update(User user, Long id);

    void deleteById(Long id);
}
