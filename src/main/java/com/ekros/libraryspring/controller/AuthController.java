package com.ekros.libraryspring.controller;


import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.services.UserService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@Validated
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String existMessage(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("message", "This user is already exist!");
        return "redirect:/book/list";
    }

    @ExceptionHandler(BindException.class)
    public String invalidArgsMessage(BindException ex, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("message", "One or more parameters are empty or invalid!");
        redirectAttributes.addAttribute("messageSignIn", ex.getAllErrors().get(0).getDefaultMessage());
        return "redirect:/book/list";
    }


    @PostMapping(value = "/signin",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String signin(@Valid UserDto userDto){
        userService.signIn(userDto);
        return "redirect:/";
    }

}
