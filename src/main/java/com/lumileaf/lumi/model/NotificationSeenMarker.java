package com.lumileaf.lumi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_seen_marker")
public class NotificationSeenMarker {

    @Id
    private Long id = 1L; // fixed single row

    @Column(name = "last_seen_id")
    private Long lastSeenId = 0L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLastSeenId() { return lastSeenId; }
    public void setLastSeenId(Long lastSeenId) { this.lastSeenId = lastSeenId; }
}