package com.ekros.libraryspring.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LibraryController {

    @GetMapping("/profile")
    public String profile(){
        return "redirect:/order";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/book/list";
    }
}
