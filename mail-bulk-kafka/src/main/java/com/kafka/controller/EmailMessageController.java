package com.kafka.controller;

import com.kafka.constants.AppConstants;
import com.kafka.pojo.DTO.EmailMessageDTO;
import com.kafka.pojo.response.DataResponse;
import com.kafka.service.EmailMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1-email-messages")
public class EmailMessageController {

    private final EmailMessageService emailMessageService;

    @PostMapping
    public ResponseEntity<?> doCreateEmailMessage(@RequestBody EmailMessageDTO emailMessageDTO) {
        emailMessageService.create(emailMessageDTO);
        DataResponse dataResponse = DataResponse.builder()
                .code(AppConstants.CODE_SUCCESS)
                .message(AppConstants.MESSAGE_SUCCESS)
                .build();
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }
}
