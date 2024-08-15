package com.kafka.service.impl;

import com.kafka.entity.BulkEmailMessage;
import com.kafka.exception.AppException;
import com.kafka.pojo.DTO.BulkEmailMessageDTO;
import com.kafka.repo.BulkEmailMessageRepo;
import com.kafka.service.BulkEmailMessageService;
import com.kafka.utils.ConvertObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class BulkEmailMessageServiceImpl implements BulkEmailMessageService {

    @Value(value = "${spring.kafka.topic}")
    private String kafkaTopic;

    private final BulkEmailMessageRepo emailMessageRepo;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void create(BulkEmailMessageDTO bulkEmailMessageDTO) {
        try {
            BulkEmailMessage bulkEmailMessage = ConvertObjectUtils.mapToEntity(bulkEmailMessageDTO, BulkEmailMessage.class);
            emailMessageRepo.save(bulkEmailMessage);

            kafkaTemplate.send(kafkaTopic, String.valueOf(bulkEmailMessage.getId()));
        } catch (Exception ex) {
            throw new AppException("Create bulk mail failed!");
        }
    }

    @Override
    public BulkEmailMessageDTO getById(Long id) {
        return ConvertObjectUtils.mapToDTO(emailMessageRepo.findById(id).get(), BulkEmailMessageDTO.class);
    }

    @Override
    public int updateByStatus(String status, Long id) {
        try {
            return emailMessageRepo.updateByStatus(status, id);
        } catch (Exception ex) {
            throw new AppException("Update status mail failed!");
        }
    }
}
