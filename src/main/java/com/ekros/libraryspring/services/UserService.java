package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.TokenRepo;
import com.ekros.libraryspring.dao.UserRepo;
import com.ekros.libraryspring.model.dto.UserDto;
import com.ekros.libraryspring.model.entity.Role;
import com.ekros.libraryspring.model.entity.Token;
import com.ekros.libraryspring.model.entity.User;
import com.ekros.libraryspring.services.interfase.IService;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IService {

    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    private final EmailService emailService;

    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    public UserService(ModelMapper mapper, PasswordEncoder encoder, EmailService emailService,
                       UserRepo userRepo, TokenRepo tokenRepo) {
        this.mapper = mapper;
        this.encoder = encoder;
        this.emailService = emailService;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    public User signIn(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        user.setRole(Role.USER);
        user.setBlock(false);
        user.setPassword(encoder.encode(userDto.getPassword()));

        user = userRepo.save(user);
        user.setToken(Token.create(UUID.randomUUID().toString(), user));
        user = userRepo.save(user);

        emailService.sendActivationCode(user.getEmail(), user.getToken().getUuid());

        return user;
    }

    public User activate(String tokenId){
        Token token = tokenRepo.findByUuid(tokenId).orElse(null);
        if(token == null){
            return null;
        }
        User user = token.getUser();
        user.setEnable(true);
        user.setToken(null);
        tokenRepo.delete(token);
        return userRepo.save(user);
    }

    public User add(User user){
        user.setBlock(false);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Transactional
    public User block(Long id, Boolean block){
        User user = userRepo.getById(id);
        user.setBlock(block);
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
}
