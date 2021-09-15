package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.UserRepo;
import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.model.entity.Role;
import com.ekros.libraryspring.model.entity.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    private UserServiceTest(){}

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
    @MockBean
    private UserRepo userRepo;
    @MockBean
    private ModelMapper mockMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void signInTest() {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setEmail("Hello");
        userDto.setPassword("12345");
        userDto.setBirthday(new Date(12345));
        userDto.setPhone("12345678");
        User user = mapper.map(userDto, User.class);
        when(mockMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("54321");
        user = userService.signIn(userDto);

        assertEquals(Role.USER, user.getRole());
        assertFalse(user.isBlock());
        assertEquals("54321", user.getPassword());

        verify(userRepo, times(1)).save(user);
    }
}