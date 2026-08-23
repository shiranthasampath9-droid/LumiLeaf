package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {
    List<NotificationEvent> findAllByOrderByIdDesc();
    long countByIdGreaterThan(Long id);
}