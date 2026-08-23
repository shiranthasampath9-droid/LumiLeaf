package com.lumileaf.lumi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rolling_point")
public class RollingPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    // --- LOGISTICS & TRACKING ---
    private String batchId;
    private String rollerMachine;
    private LocalDate rollingDate;
    private String startTime;
    private String endTime;
    private String rollingOfficer;
    private String officerName;

    // --- WEIGHT TRACKING ---
    private Double weightIn;
    private Double dhool1;
    private Double dhool2;
    private Double dhool3;
    private Double bigBulk;
    private Double weightOut;
    private Double processLoss;

    // --- DRYING & QA TRACKING ---
    private Double temperature;
    private Double moistureContent;
    private Double humidity;
    private boolean dryingCompleted = false;

    // --- DHOOL-SPECIFIC MOISTURE TRACKING ---
    private Double moistureD1 = 0.0;
    private Double moistureD2 = 0.0;
    private Double moistureD3 = 0.0;
    private Double moistureBB = 0.0;

    // --- SYSTEM TRACKING ---
    private String productionBatchNo;
    private String productionLotNo;
    private String lotNumber;

    // --- DRY OUTPUT WEIGHTS ---
    private Double dryD1;
    private Double dryD2;
    private Double dryD3;
    private Double dryBigBulk;
    private Double dryLoss;

    // ✅ FIX #4: Add rainfall field
    @Column(name = "rainfall")
    private Double rainfall;

    // ✅ FIX #4: Add weather condition field
    @Column(name = "weather_condition")
    private String weatherCondition;

    public RollingPoint() {}

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }

    public String getRollerMachine() { return rollerMachine; }
    public void setRollerMachine(String rollerMachine) { this.rollerMachine = rollerMachine; }

    public LocalDate getRollingDate() { return rollingDate; }
    public void setRollingDate(LocalDate rollingDate) { this.rollingDate = rollingDate; }

    public Double getWeightIn() { return weightIn; }
    public void setWeightIn(Double weightIn) { this.weightIn = weightIn; }

    public Double getDhool1() { return dhool1; }
    public void setDhool1(Double dhool1) { this.dhool1 = dhool1; }

    public Double getDhool2() { return dhool2; }
    public void setDhool2(Double dhool2) { this.dhool2 = dhool2; }

    public Double getDhool3() { return dhool3; }
    public void setDhool3(Double dhool3) { this.dhool3 = dhool3; }

    public Double getBigBulk() { return bigBulk; }
    public void setBigBulk(Double bigBulk) { this.bigBulk = bigBulk; }

    public Double getWeightOut() { return weightOut; }
    public void setWeightOut(Double weightOut) { this.weightOut = weightOut; }

    public Double getProcessLoss() { return processLoss; }
    public void setProcessLoss(Double processLoss) { this.processLoss = processLoss; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getRollingOfficer() { return rollingOfficer; }
    public void setRollingOfficer(String rollingOfficer) { this.rollingOfficer = rollingOfficer; }

    public String getOfficerName() { return officerName; }
    public void setOfficerName(String officerName) { this.officerName = officerName; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getMoistureContent() { return moistureContent; }
    public void setMoistureContent(Double moistureContent) { this.moistureContent = moistureContent; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public boolean isDryingCompleted() { return dryingCompleted; }
    public void setDryingCompleted(boolean dryingCompleted) { this.dryingCompleted = dryingCompleted; }

    public String getProductionBatchNo() { return productionBatchNo; }
    public void setProductionBatchNo(String productionBatchNo) { this.productionBatchNo = productionBatchNo; }

    public String getProductionLotNo() { return productionLotNo; }
    public void setProductionLotNo(String productionLotNo) { this.productionLotNo = productionLotNo; }

    public String getLotNumber() { return lotNumber; }
    public void setLotNumber(String lotNumber) { this.lotNumber = lotNumber; }

    public Double getDryD1() { return dryD1; }
    public void setDryD1(Double dryD1) { this.dryD1 = dryD1; }

    public Double getDryD2() { return dryD2; }
    public void setDryD2(Double dryD2) { this.dryD2 = dryD2; }

    public Double getDryD3() { return dryD3; }
    public void setDryD3(Double dryD3) { this.dryD3 = dryD3; }

    public Double getDryBigBulk() { return dryBigBulk; }
    public void setDryBigBulk(Double dryBigBulk) { this.dryBigBulk = dryBigBulk; }

    public Double getDryLoss() { return dryLoss; }
    public void setDryLoss(Double dryLoss) { this.dryLoss = dryLoss; }

    // --- DHOOL MOISTURE GETTERS AND SETTERS ---
    public Double getMoistureD1() { return moistureD1; }
    public void setMoistureD1(Double moistureD1) { this.moistureD1 = moistureD1; }

    public Double getMoistureD2() { return moistureD2; }
    public void setMoistureD2(Double moistureD2) { this.moistureD2 = moistureD2; }

    public Double getMoistureD3() { return moistureD3; }
    public void setMoistureD3(Double moistureD3) { this.moistureD3 = moistureD3; }

    public Double getMoistureBB() { return moistureBB; }
    public void setMoistureBB(Double moistureBB) { this.moistureBB = moistureBB; }

    // ✅ FIX #4: Rainfall getters and setters
    public Double getRainfall() { return rainfall; }
    public void setRainfall(Double rainfall) { this.rainfall = rainfall; }

    // ✅ FIX #4: Weather condition getters and setters
    public String getWeatherCondition() { return weatherCondition; }
    public void setWeatherCondition(String weatherCondition) { this.weatherCondition = weatherCondition; }
}