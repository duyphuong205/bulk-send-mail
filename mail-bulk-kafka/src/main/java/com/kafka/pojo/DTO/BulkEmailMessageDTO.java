package com.kafka.pojo.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BulkEmailMessageDTO implements Serializable {
    Long id;
    String from;
    List<String> to;
    String subject;
    String body;
}
