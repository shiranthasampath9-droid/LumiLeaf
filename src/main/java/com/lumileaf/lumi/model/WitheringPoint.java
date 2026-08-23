package com.lumileaf.lumi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "withering_point")
public class WitheringPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_id")
    private String batchId;

    private LocalDate date;

    private String section;

    @Column(name = "intake_weight")
    private Double intakeWeight;

    @Column(name = "withered_weight")
    private Double witheredWeight;

    @Column(name = "trough_number")
    private String troughNumber;

    @Column(name = "officer_name")
    private String officerName;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "withering_officer")
    private String witheringOfficer;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "time_taken")
    private String timeTaken;

    // MISSING SYMBOLS FOR QACONTROLLER:
    @Column(name = "before_weight")
    private Double beforeWeight;

    @Column(name = "after_weight")
    private Double afterWeight;

    private String labors;

    // NEW FIELDS FOR PRODUCTION TRACKING
    @Column(name = "production_batch_no")
    private String productionBatchNo;

    @Column(name = "production_lot_no")
    private String productionLotNo;
    @Column(name = "intake_adjusted_at")
    private java.time.LocalDateTime intakeAdjustedAt;

    @Column(name = "intake_adjusted_by")
    private String intakeAdjustedBy;

    public java.time.LocalDateTime getIntakeAdjustedAt() { return intakeAdjustedAt; }
    public void setIntakeAdjustedAt(java.time.LocalDateTime intakeAdjustedAt) { this.intakeAdjustedAt = intakeAdjustedAt; }

    public String getIntakeAdjustedBy() { return intakeAdjustedBy; }
    public void setIntakeAdjustedBy(String intakeAdjustedBy) { this.intakeAdjustedBy = intakeAdjustedBy; }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public Double getIntakeWeight() { return intakeWeight; }
    public void setIntakeWeight(Double intakeWeight) { this.intakeWeight = intakeWeight; }

    public Double getWitheredWeight() { return witheredWeight; }
    public void setWitheredWeight(Double witheredWeight) { this.witheredWeight = witheredWeight; }

    public String getTroughNumber() { return troughNumber; }
    public void setTroughNumber(String troughNumber) { this.troughNumber = troughNumber; }

    public String getOfficerName() { return officerName; }
    public void setOfficerName(String officerName) { this.officerName = officerName; }

    public String getLotNumber() { return lotNumber; }
    public void setLotNumber(String lotNumber) { this.lotNumber = lotNumber; }

    public String getWitheringOfficer() { return witheringOfficer; }
    public void setWitheringOfficer(String witheringOfficer) { this.witheringOfficer = witheringOfficer; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getTimeTaken() { return timeTaken; }
    public void setTimeTaken(String timeTaken) { this.timeTaken = timeTaken; }

    // GETTERS & SETTERS FOR THE QA CONTROLLER SYMBOLS
    public Double getBeforeWeight() { return beforeWeight; }
    public void setBeforeWeight(Double beforeWeight) { this.beforeWeight = beforeWeight; }

    public Double getAfterWeight() { return afterWeight; }
    public void setAfterWeight(Double afterWeight) { this.afterWeight = afterWeight; }

    public String getLabors() { return labors; }
    public void setLabors(String labors) { this.labors = labors; }

    // GETTERS & SETTERS FOR NEW PRODUCTION TRACKING FIELDS
    public String getProductionBatchNo() { return productionBatchNo; }
    public void setProductionBatchNo(String productionBatchNo) { this.productionBatchNo = productionBatchNo; }

    public String getProductionLotNo() { return productionLotNo; }
    public void setProductionLotNo(String productionLotNo) { this.productionLotNo = productionLotNo; }
}