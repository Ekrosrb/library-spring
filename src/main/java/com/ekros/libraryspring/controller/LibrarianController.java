package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.exceptions.OrderException;
import com.ekros.libraryspring.model.entity.Status;
import com.ekros.libraryspring.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    private final OrderService orderService;

    public LibrarianController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ExceptionHandler(OrderException.class)
    public String orderException(OrderException ex, RedirectAttributes attributes){
        attributes.addAttribute("message", ex.getMessage());
        return "redirect:/librarian";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String librarian(Model model, @RequestParam(required = false) String type, @RequestParam(required = false) Integer from){
        if(type == null){
            type = Status.PENDING.name();
        }

        if(from == null){
            from = 0;
        }
        model.addAttribute("type", type);
        model.addAttribute("from", from);
        model.addAttribute("orders", orderService.list(Status.valueOf(type), from));
        model.addAttribute("count", orderService.statusOrdersCount(Status.valueOf(type)));
        return "librarian";
    }

    @PostMapping("/info")
    public String info(Model model, @RequestParam(required = false) Long id){
        if(id != null){
            model.addAttribute("orderInfo", orderService.orderInfo(id));
        }
        model.addAttribute("type", "info");
        return "librarian";
    }

    @PostMapping("/userOrders")
    public String userOrders(Model model, @RequestParam(required = false) Long id, @RequestParam(required = false) Integer from){
        if(from == null){
            from = 0;
        }
        if(id != null) {
            model.addAttribute("orders", orderService.userList(from, id));
        }
        model.addAttribute("type", "user");
        model.addAttribute("from", from);
        model.addAttribute("count", orderService.userOrdersCount(id));
        return "librarian";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(Model model, Long id, Status status){
        model.addAttribute("orderInfo", orderService.changeStatus(id, status));
        model.addAttribute("type", "info");
        return "librarian";
    }

    @PostMapping("/addFine")
    public String addFine(Model model, Long id, Long fine){
        model.addAttribute("type", "info");
        model.addAttribute("orderInfo", orderService.addFine(id, fine));
        return "librarian";
    }
}
