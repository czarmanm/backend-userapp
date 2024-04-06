package com.bosch.example.backenduserapp.services;

import com.bosch.example.backenduserapp.model.dtos.UserDto;
import com.bosch.example.backenduserapp.model.dtos.mappers.UserDtoMapper;
import com.bosch.example.backenduserapp.model.entities.Role;
import com.bosch.example.backenduserapp.model.entities.User;
import com.bosch.example.backenduserapp.repositories.RoleRepository;
import com.bosch.example.backenduserapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(u -> UserDtoMapper.builder().setUser(u).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(u ->
                UserDtoMapper.builder()
                        .setUser(u)
                        .build());
    }

    @Override
    @Transactional
    public UserDto save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (roleOptional.isPresent()) {
            roles.add(roleOptional.orElseThrow());
        }
        user.setRoles(roles);

        return UserDtoMapper.builder().setUser(userRepository.save(user)).build();
    }

    @Override
    @Transactional
    public Optional<UserDto> update(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User userOptionalDb = null;
        if (userOptional.isPresent()) {
            User userDb = userOptional.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            userOptionalDb = userRepository.save(userDb);
        }
        return Optional.ofNullable(UserDtoMapper.builder().setUser(userOptionalDb).build());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
