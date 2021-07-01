package com.ekros.libraryspring.config;

import com.ekros.libraryspring.model.entity.Order;
import com.ekros.libraryspring.model.entity.OrderInfo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class MapperConfig {
    @Bean
    public ModelMapper getModelMapper(){

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(Order.class, OrderInfo.class).addMappings(m -> {
            m.map(Order::getId, (orderInfo, o) -> orderInfo.setId((Long) o));
            m.map(order -> order.getUser().getId(), ((orderInfo, o) -> orderInfo.setUserId((Long) o)));
            m.map(order -> order.getBook().getName(), (orderInfo, o) -> orderInfo.setBookName((String) o));
            m.map(order -> order.getUser().getFullName(), (orderInfo, o) -> orderInfo.setUserName((String) o));
            m.map(order -> order.getUser().getEmail(), (orderInfo, o) -> orderInfo.setEmail((String) o));
            m.map(order -> order.getUser().getPhone(), (orderInfo, o) -> orderInfo.setPhone((String) o));
            m.map(Order::getTerm, (orderInfo, o) -> orderInfo.setTerm((Date) o));
            m.map(Order::getOrderDate, (orderInfo, o) -> orderInfo.setOrderDate((Date) o));
            m.map(Order::getFine, (orderInfo, o) -> orderInfo.setFine((Long) o));
        });
        return mapper;
    }
}
