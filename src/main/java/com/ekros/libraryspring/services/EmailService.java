package com.ekros.libraryspring.services;

import com.ekros.libraryspring.model.entity.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${server.address}")
    private String host;
    @Value("${server.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendActivationCode(String to, String token){
        String text = "Here is your activation link: http://" + host + ":" + port + "/auth/activate/" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(username);
        message.setSubject("Library activation account");
        message.setText(text);

        emailSender.send(message);
    }
}
