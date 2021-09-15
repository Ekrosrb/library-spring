package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.dao.UserRepo;
import com.ekros.libraryspring.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepo userRepo;

    @Test
    void signInValidTest() throws Exception{
        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "Test")
                        .param("email", "Test@mail.com")
                        .param("password", "12345")
                        .param("birthday", "2000-01-27")
                        .param("phone", "12345678"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
        User user = userRepo.getUserByEmail("Test@mail.com").orElse(null);
        assertNotNull(user);
        assertEquals("Test@mail.com", user.getEmail());
        userRepo.delete(user);
    }

    @Test
    void signInInvalidNameTest() throws Exception{
        mockMvc.perform(post("/auth/signin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "")
                .param("lastName", "Test")
                .param("email", "Test@mail.com")
                .param("password", "12345")
                .param("birthday", "2000-01-27")
                .param("phone", "12345678"))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/book/list"));
    }

    @Test
    void signInInvalidLastNameTest() throws Exception{
        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "")
                        .param("email", "Test@mail.com")
                        .param("password", "12345")
                        .param("birthday", "2000-01-27")
                        .param("phone", "12345678"))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/book/list"));
    }

    @Test
    void signInInvalidEmailTest() throws Exception{
        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "Test")
                        .param("email", "Test.com")
                        .param("password", "12345")
                        .param("birthday", "2000-01-27")
                        .param("phone", "12345678"))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/book/list"));
    }

    @Test
    void signInInvalidPhoneTest() throws Exception{
        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Test")
                        .param("lastName", "Test")
                        .param("email", "Test@mail.com")
                        .param("password", "12345")
                        .param("birthday", "2000-01-27")
                        .param("phone", "1234"))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/book/list"));
    }
}
