package com.kafka.entity;

import com.kafka.constants.AppConstants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bulk_email_message")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BulkEmailMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "\"from\"")
    String from;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "\"to\"", joinColumns = @JoinColumn(name = "bulk_email_message_id"))
    @Column(name = "\"to\"")
    List<String> to;
    String subject;
    @Column(columnDefinition = "TEXT")
    String body;
    String status = AppConstants.STATUS_PENDING;
    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
}
