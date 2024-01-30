package com.vti.movie.service.schedule;

import com.vti.movie.modal.entity.Token;
import com.vti.movie.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@EnableAsync
@RequiredArgsConstructor
@Component
@Slf4j
public class CheckExpToken {
    static final long EXPIRATION_TIME = 864000000;

    @Autowired
    private TokenRepository tokenRepository;
    @Scheduled(cron = "0 0/1 * * * *")
    public void checkExpTokenJob(){
        log.info("time to run log: {}", new Date());
        List<Token> tokenExp = tokenRepository.findByExpirationIsAfter(new Date(System.currentTimeMillis()+EXPIRATION_TIME));
        tokenRepository.deleteAll();
        log.info("Number token to delete: {},", tokenExp.size());
    }
}
