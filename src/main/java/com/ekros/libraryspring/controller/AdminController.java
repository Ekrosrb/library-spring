package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.model.entity.Role;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.EventGeneratorService;
import com.ekros.libraryspring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final EventGeneratorService eventGeneratorService;

    public AdminController(UserService userService, EventGeneratorService eventGeneratorService) {
        this.userService = userService;
        this.eventGeneratorService = eventGeneratorService;
    }

    @GetMapping("/test/generate/{size}")
    public String generateEvents(@PathVariable Integer size){
        eventGeneratorService.generateEvents(size);
        return "redirect:/admin";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public String admin(Model model, @RequestParam(required = false) Integer from){
        if(from == null){
            from = 0;
        }
        model.addAttribute("userList", userService.list(from));
        model.addAttribute("count", userService.count());
        model.addAttribute("from", from);
        return "admin";
    }

    @PostMapping("/add")
    public String add(String firstName, String lastName, String email, String password, Date birthday, String phone, Role role){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setRole(role);
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/block")
    public String block(Long id, Boolean block){
        userService.block(id, block);
        return "redirect:/admin";
    }

    @PostMapping("/updateUser")
    public String update(Long id, String firstName, String lastName, Date birthday, String phone, Role role){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setRole(role);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String delete(@RequestParam Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
