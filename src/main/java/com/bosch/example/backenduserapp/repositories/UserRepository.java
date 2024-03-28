package com.bosch.example.backenduserapp.repositories;

import com.bosch.example.backenduserapp.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    /*@Query("select u from User u where u.username=?1")
    Optional<User> getUserByUsername(String username);*/
}
