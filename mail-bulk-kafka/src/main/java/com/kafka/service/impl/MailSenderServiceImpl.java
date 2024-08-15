package com.kafka.service.impl;

import com.kafka.pojo.DTO.EmailMessageDTO;
import com.kafka.service.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(EmailMessageDTO emailMessageDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(emailMessageDTO.getFrom());
        helper.setTo(emailMessageDTO.getTo());
        helper.setSubject(emailMessageDTO.getSubject());
        helper.setText(emailMessageDTO.getBody(), true);
        helper.setReplyTo(emailMessageDTO.getFrom());

        mailSender.send(message);
    }
}
