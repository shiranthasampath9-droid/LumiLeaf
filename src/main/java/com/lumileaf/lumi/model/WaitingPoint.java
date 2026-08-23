package com.lumileaf.lumi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "waiting_point")
public class WaitingPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "section")
    private String section;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "gross_weight")
    private Double grossWeight;

    @Column(name = "bags")
    private Integer bags;

    @Column(name = "waiting_officer")
    private String waitingOfficer;

    @Column(name = "officer_name")
    private String officerName;

    @Column(name = "lot_number")
    private String lotNumber;

    // ✅ Status column - already exists in DB
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status = "PENDING";

    // ✅ Finalized timestamp - NEEDS TO BE ADDED
    @Column(name = "finalized_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime finalizedAt;

    // ✅ Route column - exists in DB but not in model
    @Column(name = "route")
    private String route;

    // ✅ Last modified - already exists in DB
    @Column(name = "last_modified", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastModified;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getBatchId() {
        return batchId;
    }
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWaitingOfficer() {
        return waitingOfficer;
    }
    public void setWaitingOfficer(String waitingOfficer) {
        this.waitingOfficer = waitingOfficer;
    }

    public String getOfficerName() {
        return officerName;
    }
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }
    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getBags() {
        return bags;
    }
    public void setBags(Integer bags) {
        this.bags = bags;
    }

    public String getLotNumber() {
        return lotNumber;
    }
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    // ✅ Status getters and setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // ✅ Finalized at getters and setters
    public LocalDateTime getFinalizedAt() {
        return finalizedAt;
    }
    public void setFinalizedAt(LocalDateTime finalizedAt) {
        this.finalizedAt = finalizedAt;
    }

    // ✅ Route getters and setters
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }

    // ✅ Last modified getters and setters
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
    @PrePersist
    @PreUpdate
    protected void onSaveOrUpdate() {
        if (this.lastModified == null) {
            this.lastModified = LocalDateTime.now();
        }
    }
}