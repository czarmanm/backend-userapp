package com.bosch.example.backenduserapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void userAuthTest() throws Exception {
        this.mockMvc
                .perform(post("/login")
                        .content("""
                                {
                                    "username": "admin",
                                    "password": "test"
                                }
                                """)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
