package com.ekros.libraryspring.schedule;

import com.ekros.libraryspring.services.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Log4j
@Component
public class ScheduledTokenCleaner {

    private static final long CLEAN_RATE = 86400000;
    private static final long CLEAN_TIME = 86400000;
    private final TokenService tokenService;

    public ScheduledTokenCleaner(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Scheduled(fixedRate = CLEAN_RATE)
    public void cleanTokenDatabase(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - CLEAN_TIME);
        tokenService.deleteOldTokens(timestamp);
        log.info("Scheduler: deleted all before " + timestamp);
    }

}
