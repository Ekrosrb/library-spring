package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.OrderRepo;
import com.ekros.libraryspring.exceptions.OrderException;
import com.ekros.libraryspring.model.entity.*;
import com.ekros.libraryspring.services.interfase.IService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService implements IService {

    private final OrderRepo orderRepo;
    private final ModelMapper mapper;

    public OrderService(OrderRepo orderRepo, ModelMapper mapper) {
        this.orderRepo = orderRepo;
        this.mapper = mapper;
    }

    public Order order(Long id){
        return orderRepo.findById(id).orElse(null); //TODO fix null
    }

    public OrderInfo orderInfo(Long id){
        Order order = orderRepo.findById(id).get();
        return mapper.map(order, OrderInfo.class);
    }

    public List<Order> list(Status status, Integer from){
        Pageable pageable = PageRequest.of(from/20, 20);
        return orderRepo.findAllByStatus(status, pageable).getContent();
    }

    public List<Order> userList(Integer from, Long id){
        return orderRepo.findAllByUserId(id, PageRequest.of(from/20, 20)).getContent();
    }

    public Long userOrdersCount(Long id){
        return orderRepo.count(id);
    }

    public Long statusOrdersCount(Status status){
        return orderRepo.count(status.ordinal());
    }

    public Order create(Long bookId, Long userId, Date term) {
        if(orderRepo.checkUserActiveOrders(userId, bookId) > 0){
            throw new OrderException("You already have an active order for this book.");
        }
        Order order = new Order();
        order.setBook(new Book());
        order.setUser(new User());
        order.getBook().setId(bookId);
        order.getUser().setId(userId);
        order.setTerm(term);
        order.setOrderDate(new Date(new java.util.Date().getTime()));
        order.setStatus(Status.PENDING);
        order.setFine(0L);
        return orderRepo.save(order);
    }

    @Transactional
    public OrderInfo changeStatus(Long id, Status status){
        Order order = orderRepo.findById(id).get();

        if(status == Status.ACCEPTED){
            if(order.getBook().getCount() == 0){
                throw new OrderException("Book '" + order.getBook().getName() + "' out of stock");
            }
            order.getBook().setCount(order.getBook().getCount()-1);
        }else if(status == Status.CLOSED){
            order.getBook().setCount(order.getBook().getCount()+1);
        }
        order.setStatus(status);
        return mapper.map(orderRepo.save(order), OrderInfo.class);
    }

    @Transactional
    public Order payFine(Long id){
        Order order = orderRepo.findById(id).get();
        order.setFine(0L);
        order.setStatus(Status.PAID);
        return orderRepo.save(order);
    }

    @Transactional
    public OrderInfo addFine(Long id, Long fine){
        Order order = orderRepo.findById(id).get();
        order.setFine(fine);
        order.setStatus(Status.EXPIRED);
        return mapper.map(orderRepo.save(order), OrderInfo.class);
    }

}
