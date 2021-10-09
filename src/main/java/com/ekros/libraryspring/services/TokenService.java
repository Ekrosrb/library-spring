package com.ekros.libraryspring.services;

import com.ekros.libraryspring.dao.TokenRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class TokenService {

    private final TokenRepo tokenRepo;

    public TokenService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    public void deleteOldTokens(Timestamp border){
        tokenRepo.deleteOldAll(border);
    }
}
