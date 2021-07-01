package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.model.entity.LibraryUserDetails;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.OrderService;
import com.ekros.libraryspring.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String order(Model model, @PathVariable Long id, @RequestParam(required = false) Integer from){
        profileOrderList(model, from);
        model.addAttribute("order", orderService.order(id));
        return "profile";
    }

    @GetMapping
    public String list(Model model, @RequestParam(required = false) Integer from){
        profileOrderList(model, from);
        return "profile";
    }

    @PostMapping("/add")
    public String create(Long bookId, Date term){
        return "redirect:/order/" + orderService.create(bookId, userId(), term).getId();
    }

    @PostMapping("/pay")
    public String pay(Model model, Long id, @RequestParam(required = false) Integer from){
        profileOrderList(model, from);
        model.addAttribute("order", orderService.payFine(id));

        return "profile";
    }

    public void profileOrderList(Model model, Integer from){
        if(from == null){
            from = 0;
        }
        User user = userService.getUser(userId());
        model.addAttribute("user", userService.toDto(user));
        model.addAttribute("orderList", orderService.userList(from, user.getId()));
        model.addAttribute("from", from);
        model.addAttribute("count", user.getOrders().size());
    }

    private Long userId(){
        return ((LibraryUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
