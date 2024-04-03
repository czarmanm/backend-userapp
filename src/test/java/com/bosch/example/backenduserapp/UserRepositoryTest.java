package com.bosch.example.backenduserapp;

import com.bosch.example.backenduserapp.model.entities.User;
import com.bosch.example.backenduserapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void saveUser() {
        userRepository.save(
                User.builder()
                        .username("Lucy")
                        .email("lucy@email.com")
                        .password("test")
                        .build());

        assertThat(
                userRepository.findByUsername("Lucy").isPresent()
        ).isTrue();
    }

    @Test
    void deleteUsers() {
        userRepository.save(
                User.builder()
                        .username("Lucy")
                        .email("lucy@email.com")
                        .password("test")
                        .build());

        userRepository.deleteAll();

        assertThat(userRepository.count()).isEqualTo(0);
    }
}
