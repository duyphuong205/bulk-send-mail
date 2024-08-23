package com.kafka.controller;

import com.kafka.constants.AppConstants;
import com.kafka.pojo.DTO.BulkEmailMessageDTO;
import com.kafka.pojo.response.DataResponse;
import com.kafka.service.BulkEmailMessageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1-bulk-email-messages")
public class BulkEmailMessageController {
    private static final Logger logger = LogManager.getLogger(BulkEmailMessageController.class);

    private final BulkEmailMessageService bulkEmailMessageService;

    @PostMapping
    public ResponseEntity<?> doCreateEmailMessage(@RequestBody BulkEmailMessageDTO emailMessageDTO) {
        logger.info("**********BulkEmailMessageController Begin call API**********");
        bulkEmailMessageService.create(emailMessageDTO);
        DataResponse dataResponse = DataResponse.builder()
                .code(AppConstants.CODE_SUCCESS)
                .message(AppConstants.MESSAGE_SUCCESS)
                .build();
        logger.info("**********BulkEmailMessageController End call API**********");
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }
}
