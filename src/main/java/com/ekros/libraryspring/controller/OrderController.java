package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.exceptions.OrderException;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.OrderService;
import com.ekros.libraryspring.services.UserService;
import com.ekros.libraryspring.utils.ContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.Locale;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @ExceptionHandler(OrderException.class)
    public String orderExceptionHandler(OrderException ex, RedirectAttributes attributes){
        attributes.addAttribute("message", ex.getMessage());
        return "redirect:/book/list";
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
    public String create(Long bookId, Date term, Locale locale){
        return "redirect:/order/" + orderService.create(bookId, ContextUtils.getPrincipal().getId(), term, locale).getId();
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
        User user = userService.getUser(ContextUtils.getPrincipal().getId());
        model.addAttribute("user", userService.toDto(user));
        model.addAttribute("orderList", orderService.userList(from, user.getId()));
        model.addAttribute("from", from);
        model.addAttribute("count", user.getOrders().size());
    }
}
