package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.Order;
import com.ekros.libraryspring.model.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {
    Page<Order> findAllByUserId(Long id, Pageable pageable);
    Page<Order> findAllByStatus(Status status, Pageable pageable);
}