package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}