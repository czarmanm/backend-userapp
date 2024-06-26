package com.bosch.example.backenduserapp;

import com.bosch.example.backenduserapp.controller.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackendUserappApplicationTests {
    @Autowired
    UserController userController;

    @Test
    @DisplayName("First example test case")
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

}
