package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.BookRepo;
import com.ekros.libraryspring.model.dto.BookDto;
import com.ekros.libraryspring.model.entity.Book;
import com.ekros.libraryspring.services.interfase.IService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService implements IService<Book, BookDto> {

    private final ModelMapper mapper;
    private final BookRepo bookRepo;

    public BookService(ModelMapper mapper, BookRepo bookRepo) {
        this.mapper = mapper;
        this.bookRepo = bookRepo;
    }

    public List<Book> booksList(String search, Integer from, String orderBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);
        Pageable pageable = PageRequest.of(from/20, 20, sort);
        return bookRepo.findByNameContainingOrAuthorContaining(search, search, pageable);
    }

    public Book add(BookDto bookDto){
        return bookRepo.save(fromDto(bookDto));
    }

    public void remove(Long id){
        bookRepo.deleteById(id);
    }

    @Transactional
    public Book update(Long id, BookDto book){
        Book old = bookRepo.findById(id).orElse(null);
        if(old == null){
            return null;
        }
        Book newBook = fromDto(book);
        newBook.setId(old.getId());
        return bookRepo.save(newBook);
    }

    public Long count(String search){
        search = "%" + search + "%";
        return bookRepo.count(search);
    }

    @Override
    public BookDto toDto(Book book) {
        return mapper.map(book, BookDto.class);
    }

    @Override
    public Book fromDto(BookDto bookDto) {
        return mapper.map(bookDto, Book.class);
    }
}
