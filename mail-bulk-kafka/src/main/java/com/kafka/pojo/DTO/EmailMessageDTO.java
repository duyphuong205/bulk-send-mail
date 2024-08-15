package com.kafka.pojo.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailMessageDTO implements Serializable {
    Long id;
    String from;
    String to;
    String subject;
    String body;
}
