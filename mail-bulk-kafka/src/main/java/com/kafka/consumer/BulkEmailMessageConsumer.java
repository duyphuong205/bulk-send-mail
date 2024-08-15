package com.kafka.consumer;

import com.kafka.constants.AppConstants;
import com.kafka.pojo.DTO.BulkEmailMessageDTO;
import com.kafka.service.BulkEmailMessageService;
import com.kafka.service.BulkMailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class BulkEmailMessageConsumer {

    private final BulkEmailMessageService bulkEmailMessageService;

    private final BulkMailSenderService mailSenderService;

    @KafkaListener(topics = "emailMessageTopic")
    public void listenTopicGetIdMail(String mailId) {
        log.info("**********BulkEmailMessageConsumer Begin Send Mail**********");
        BulkEmailMessageDTO bulkEmailMessageInfo = bulkEmailMessageService.getById(Long.parseLong(mailId));
        try {
            if (Objects.nonNull(bulkEmailMessageInfo)) {
                int updateSending = bulkEmailMessageService.updateByStatus(AppConstants.STATUS_SENDING, bulkEmailMessageInfo.getId());
                if (updateSending == 1) {
                    try {
                        mailSenderService.sendMail(bulkEmailMessageInfo);
                        bulkEmailMessageService.updateByStatus(AppConstants.STATUS_SENT, bulkEmailMessageInfo.getId());
                    } catch (MessagingException e) {
                        log.info("**********BulkEmailMessageConsumer update status to Failed**********");
                        bulkEmailMessageService.updateByStatus(AppConstants.STATUS_FAILED, bulkEmailMessageInfo.getId());
                        throw new RuntimeException(e);
                    }
                }
            }
            log.info("**********BulkEmailMessageConsumer End Send Mail**********");
        } catch (Exception ex) {
            log.error("**********EmailMessageSchedule Send Mail Error**********", ex);
        }
    }
}
