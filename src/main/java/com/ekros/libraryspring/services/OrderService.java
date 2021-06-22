package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.OrderRepo;
import com.ekros.libraryspring.model.dto.OrderDto;
import com.ekros.libraryspring.model.entity.Book;
import com.ekros.libraryspring.model.entity.Order;
import com.ekros.libraryspring.model.entity.Status;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.interfase.IService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService implements IService<Order, OrderDto> {

    private final OrderRepo orderRepo;
    private final ModelMapper mapper;

    public OrderService(OrderRepo orderRepo, ModelMapper mapper) {
        this.orderRepo = orderRepo;
        this.mapper = mapper;
    }

    public Order order(Long id){
        return orderRepo.findById(id).orElse(null); //TODO fix null
    }

    public List<Order> list(Integer from){
        Pageable pageable = PageRequest.of(from/20, 20);
        return orderRepo.findAll(pageable).getContent();
    }

    public Order create(Long bookId, Long userId, Date term) {
        Order order = new Order();
        order.setBook(new Book());
        order.setUser(new User());
        order.getBook().setId(bookId);
        order.getUser().setId(userId);
        order.setTerm(term);
        order.setStatus(Status.PENDING);
        order.setFine(0L);
        return orderRepo.save(order);
    }

    @Transactional
    public Order payFine(Long id){
        Order order = orderRepo.getById(id);
        order.setFine(0L);
        order.setStatus(Status.PAID);
        return orderRepo.save(order);
    }

    @Override
    public OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public Order fromDto(OrderDto orderDto) {
        return mapper.map(orderDto, Order.class);
    }
}
