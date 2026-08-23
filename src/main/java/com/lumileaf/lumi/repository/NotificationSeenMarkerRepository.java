package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.NotificationSeenMarker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSeenMarkerRepository extends JpaRepository<NotificationSeenMarker, Long> {
}