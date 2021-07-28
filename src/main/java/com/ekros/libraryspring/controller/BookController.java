package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.model.dto.BookDto;
import com.ekros.libraryspring.services.BookService;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    private final MessageSource messageSource;

    public BookController(BookService bookService, MessageSource messageSource) {
        this.bookService = bookService;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public String incorrectBookData(BindException ex, RedirectAttributes attributes, Locale locale){
        attributes.addAttribute("message",
                messageSource.getMessage("error.message.invalid.param", null, locale));
        attributes.addAttribute("messageBook", ex.getAllErrors().get(0).getDefaultMessage());
        return "redirect:/book/list";
    }


    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "") String search,
                       @RequestParam(required = false) Integer from,
                       @RequestParam(defaultValue = "name") String orderBy){
        if(from == null){
            from = 0;
        }
        model.addAttribute("search", search);
        model.addAttribute("books", bookService.booksList(search, from, orderBy));
        model.addAttribute("count", bookService.count(search));
        model.addAttribute("from", from);
        model.addAttribute("orderBy", orderBy);
        return "index";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@Valid BookDto bookDto){
        bookService.add(bookDto);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.remove(id);
        return "redirect:/";
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable Long id, @Valid BookDto bookDto){
        bookService.update(id, bookDto);
        return "redirect:/";
    }
}
