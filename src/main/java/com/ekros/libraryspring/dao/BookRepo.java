package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.Book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepo extends PagingAndSortingRepository<Book, Long> {
    List<Book> findByNameContainingOrAuthorContaining(String name, String author, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM books b WHERE b.name = ?1 OR b.author = ?1")
    Long count(String search);
}