package com.ekros.libraryspring.security;

import com.ekros.libraryspring.dao.UserRepo;
import com.ekros.libraryspring.model.entity.LibraryUserDetails;
import com.ekros.libraryspring.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public LibraryUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByEmail(s);
        if(user.isPresent()) {
            return new LibraryUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Username not found!");
    }
}
