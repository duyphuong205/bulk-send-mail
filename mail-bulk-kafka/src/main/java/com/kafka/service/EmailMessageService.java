package com.kafka.service;

import com.kafka.pojo.DTO.EmailMessageDTO;

import java.util.List;

public interface EmailMessageService {
    void create(EmailMessageDTO emailMessageDTO);
    List<EmailMessageDTO> getByStatus(String status);
    int updateByStatus(String status, Long id);
}
