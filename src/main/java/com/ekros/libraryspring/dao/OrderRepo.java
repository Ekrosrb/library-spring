package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.Order;
import com.ekros.libraryspring.model.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM orders o WHERE o.user_id = ?1 AND o.book_id = ?2 AND o.status != 1 AND o.status != 4")
    Long checkUserActiveOrders(Long userId, Long bookId);
    Page<Order> findAllByUserId(Long id, Pageable pageable);
    Page<Order> findAllByStatus(Status status, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM orders o WHERE o.user_id = ?1")
    Long count(Long id);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM orders o WHERE o.status = ?1")
    Long count(Integer status);
}