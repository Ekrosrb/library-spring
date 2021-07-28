package com.ekros.libraryspring.controller;


import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.services.UserService;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;

@Controller
@Validated
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final MessageSource messageSource;

    public AuthController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String existMessage(RedirectAttributes redirectAttributes, Locale locale){
        redirectAttributes.addAttribute("message",
                messageSource.getMessage("error.message.user.exist", null, locale));
        return "redirect:/book/list";
    }

    @ExceptionHandler(BindException.class)
    public String invalidArgsMessage(BindException ex, RedirectAttributes redirectAttributes, Locale locale){

        redirectAttributes.addAttribute("message",
                messageSource.getMessage("error.message.invalid.param", null, locale));
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
