package com.ekros.libraryspring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void profileTest() throws Exception{
        mockMvc.perform(get("/profile").with(user("Admin@mail.com").password("12345"))).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/order"));
    }

    @Test
    void profileNonAuthTest() throws Exception{
        mockMvc.perform(get("/profile")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/"));
    }

    @Test
    void indexTest() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/book/list"));
    }


}
