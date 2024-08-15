package com.kafka.service;

import com.kafka.pojo.DTO.EmailMessageDTO;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void sendMail(EmailMessageDTO emailMessageDTO) throws MessagingException;
}
