package com.ekros.libraryspring.controller;

import com.ekros.libraryspring.model.dto.BookDto;
import com.ekros.libraryspring.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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

    @PostMapping("/add")
    public String add(String name, String author, String edition, String description, String descriptionRu, Integer count){
        BookDto bookDto = new BookDto();
        bookDto.setName(name);
        bookDto.setAuthor(author);
        bookDto.setEdition(edition);
        bookDto.setDescription(description);
        bookDto.setDescriptionRu(descriptionRu);
        bookDto.setCount(count);
        bookService.add(bookDto);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.remove(id);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, String name, String author, String edition, String description, String descriptionRu, Integer count){
        BookDto bookDto = new BookDto();
        bookDto.setName(name);
        bookDto.setAuthor(author);
        bookDto.setEdition(edition);
        bookDto.setDescription(description);
        bookDto.setDescriptionRu(descriptionRu);
        bookDto.setCount(count);
        bookService.update(id, bookDto);
        return "redirect:/";
    }
}
