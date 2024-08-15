package com.kafka.service;

import com.kafka.pojo.DTO.BulkEmailMessageDTO;
import jakarta.mail.MessagingException;

public interface BulkMailSenderService {
    void sendMail(BulkEmailMessageDTO bulkEmailMessageDTO) throws MessagingException;
}
