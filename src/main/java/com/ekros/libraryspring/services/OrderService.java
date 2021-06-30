package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.OrderRepo;
import com.ekros.libraryspring.model.dto.OrderDto;
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

    public OrderInfo orderInfo(Long id){
        Order order = orderRepo.findById(id).get();
        return toOrderInfo(order);
    }

    private OrderInfo toOrderInfo(Order order){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(order.getId());
        orderInfo.setUserId(order.getUser().getId());
        orderInfo.setBookName(order.getBook().getName());
        orderInfo.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
        orderInfo.setEmail(order.getUser().getEmail());
        orderInfo.setPhone(order.getUser().getPhone());
        orderInfo.setTerm(order.getTerm());
        orderInfo.setOrderDate(order.getOrderDate());
        orderInfo.setFine(order.getFine());
        orderInfo.setStatus(order.getStatus());
        return orderInfo;
    }

    public List<Order> list(Status status, Integer from){
        Pageable pageable = PageRequest.of(from/20, 20);
        return orderRepo.findAllByStatus(status, pageable).getContent();
    }

    public List<Order> userList(Integer from, Long id){
        return orderRepo.findAllByUserId(id, PageRequest.of(from/20, 20)).getContent();
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
    public OrderInfo changeStatus(Long id, Status status){
        Order order = orderRepo.findById(id).get();
        if(status == Status.ON_USE){
            order.getBook().setCount(order.getBook().getCount()-1);
        }else if(status == Status.CLOSED){
            order.getBook().setCount(order.getBook().getCount()+1);
        }
        order.setStatus(status);
        return toOrderInfo(orderRepo.save(order));
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
        return toOrderInfo(orderRepo.save(order));
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
