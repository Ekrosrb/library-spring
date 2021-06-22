package com.ekros.libraryspring.controller;


import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.services.UserService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String signin(Model model, String firstName, String lastName, String email, String password, Date birthday, String phone){
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setBirthday(birthday);
        userDto.setPhone(phone);
        userService.signIn(userDto);
        model.addAttribute("message", "Ok!");
        return "redirect:/";
    }

}
