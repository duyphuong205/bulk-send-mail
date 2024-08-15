package com.kafka.service.impl;

import com.kafka.entity.EmailMessage;
import com.kafka.exception.AppException;
import com.kafka.pojo.DTO.EmailMessageDTO;
import com.kafka.repo.EmailMessageRepo;
import com.kafka.service.EmailMessageService;
import com.kafka.utils.ConvertObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class EmailMessageServiceImpl implements EmailMessageService {

    private final EmailMessageRepo emailMessageRepo;

    @Override
    public void create(EmailMessageDTO emailMessageDTO) {
        try {
            emailMessageRepo.save(ConvertObjectUtils.mapToEntity(emailMessageDTO, EmailMessage.class));
        } catch (Exception ex) {
            throw new AppException("Create mail failed!");
        }
    }

    @Override
    public List<EmailMessageDTO> getByStatus(String status) {
        return emailMessageRepo.findByStatus(status).stream().map(item -> ConvertObjectUtils.mapToDTO(item, EmailMessageDTO.class)).toList();
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
