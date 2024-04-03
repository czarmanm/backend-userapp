package com.bosch.example.backenduserapp.services;

import com.bosch.example.backenduserapp.model.dtos.UserDto;
import com.bosch.example.backenduserapp.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);

    Optional<UserDto> update(User user, Long id);

    void deleteById(Long id);
}
