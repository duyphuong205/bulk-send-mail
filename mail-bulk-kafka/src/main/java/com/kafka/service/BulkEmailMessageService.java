package com.kafka.service;

import com.kafka.pojo.DTO.BulkEmailMessageDTO;

public interface BulkEmailMessageService {
    void create(BulkEmailMessageDTO bulkEmailMessageDTO);
    BulkEmailMessageDTO getById(Long id);
    int updateByStatus(String status, Long id);
}
