package com.kafka.service.impl;

import com.kafka.pojo.DTO.BulkEmailMessageDTO;
import com.kafka.service.BulkMailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class BulkMailSenderServiceImpl implements BulkMailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(BulkEmailMessageDTO bulkEmailMessageDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(bulkEmailMessageDTO.getFrom());
        helper.setTo(bulkEmailMessageDTO.getTo().toArray(new String[0]));
        helper.setSubject(bulkEmailMessageDTO.getSubject());
        helper.setText(bulkEmailMessageDTO.getBody(), true);
        helper.setReplyTo(bulkEmailMessageDTO.getFrom());

        mailSender.send(message);
    }
}
