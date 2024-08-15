package com.kafka.repo;

import com.kafka.entity.BulkEmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkEmailMessageRepo extends JpaRepository<BulkEmailMessage, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE BulkEmailMessage e SET e.status = ?1 WHERE e.id = ?2")
    int updateByStatus(String status, Long id);
}
