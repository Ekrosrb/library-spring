package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.UserRepo;
import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.model.entity.Role;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.interfase.IService;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IService<User, UserDto> {

    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    private final UserRepo userRepo;

    public UserService(ModelMapper mapper, PasswordEncoder encoder, UserRepo userRepo) {
        this.mapper = mapper;
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    public User signIn(UserDto userDto){
        User user = fromDto(userDto);
        user.setRole(Role.USER);
        user.setBlock(false);
        user.setPassword(encoder.encode(userDto.getPassword()));

        return userRepo.save(user);
    }

    @Transactional
    public User update(User user){
        User old = userRepo.getById(user.getId());
        user.setPassword(old.getPassword());
        user.setEmail(old.getEmail());
        return userRepo.save(user);
    }

    public void delete(Long id){
        userRepo.deleteById(id);
    }

    public List<User> list(Integer from){
        Pageable pageable = PageRequest.of(from/20, 20);
        return userRepo.findAll(pageable).getContent();
    }

    public Long count(){
        return userRepo.count();
    }

    public User getUser(Long id){
        return userRepo.getById(id);
    }

    public UserDto toDto(User user){
        return mapper.map(user, UserDto.class);
    }

    public User fromDto(UserDto userDto){
       return mapper.map(userDto, User.class);
    }
}
