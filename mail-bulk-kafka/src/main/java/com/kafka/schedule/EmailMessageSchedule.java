package com.kafka.schedule;

import com.kafka.constants.AppConstants;
import com.kafka.pojo.DTO.EmailMessageDTO;
import com.kafka.service.EmailMessageService;
import com.kafka.service.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailMessageSchedule {

    private final EmailMessageService emailMessageService;
    private final MailSenderService mailSenderService;

    @Scheduled(fixedDelay = 1000)
    public void jobSendMail() {
        log.info("**********EmailMessageSchedule Begin jobSendMail**********");
        List<EmailMessageDTO> listMail = emailMessageService.getByStatus(AppConstants.STATUS_PENDING);
        try {
            if (!CollectionUtils.isEmpty(listMail)) {
                listMail.forEach(item -> {
                    log.info("**********EmailMessageSchedule jobSendMail update status to Sending**********");
                    int updateSending = emailMessageService.updateByStatus(AppConstants.STATUS_SENDING, item.getId());
                    if (updateSending == 1) {
                        try {
                            log.info("**********EmailMessageSchedule jobSendMail send mail**********");
                            mailSenderService.sendMail(item);
                            log.info("**********EmailMessageSchedule jobSendMail update status to Sent**********");
                            emailMessageService.updateByStatus(AppConstants.STATUS_SENT, item.getId());
                        } catch (MessagingException e) {
                            log.info("**********EmailMessageSchedule jobSendMail update status to Failed**********");
                            emailMessageService.updateByStatus(AppConstants.STATUS_FAILED, item.getId());
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            log.info("**********EmailMessageSchedule End jobSendMail**********");
        } catch (Exception ex) {
            log.error("**********EmailMessageSchedule jobSendMail Error**********", ex);
        }
    }
}
