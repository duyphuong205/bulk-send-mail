package com.kafka.repo;

import com.kafka.entity.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailMessageRepo extends JpaRepository<EmailMessage, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE EmailMessage e SET e.status = ?1 WHERE e.id = ?2")
    int updateByStatus(String status, Long id);
    List<EmailMessage> findByStatus(String status);
}
