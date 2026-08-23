package com.lumileaf.lumi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Blending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private String buyerInfo;

    // THE FIX: New field to store the Recipe/Product Name
    // AFTER
    // THE FIX: New field to store the Recipe/Product Name
    private String productName;

    // NEW: Persisted target weight for the invoice (set once, shared across all grade-lines under it)
    private Double targetTotalWeight;

    private String grade;
    private String blendingNumber;

    // This stores comma-separated Lot Numbers (e.g., "LOT-101, LOT-102") or "FROM-REMNANTS"
    private String batchNumber;

    private Double quantity;
    @Column(nullable = false)
    private String finishedGoodNumber;
    // The unique ID for the QR code // The unique ID for the QR code
    private String tcNumber;
    private boolean tcApproved;

    private LocalDate blendingDate = LocalDate.now();

    private String status = "PENDING";

    // --- Helper Logic for Traceability ---
    public String[] getIndividualBatchNumbers() {
        if (this.batchNumber == null || this.batchNumber.isEmpty()) {
            return new String[0];
        }
        return this.batchNumber.split("\\s*,\\s*");
    }

    public String[] getBatchList() {
        return getIndividualBatchNumbers();
    }

    // --- Standard Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getBuyerInfo() { return buyerInfo; }
    public void setBuyerInfo(String buyerInfo) { this.buyerInfo = buyerInfo; }

    // New Getter and Setter for Product Name
    // AFTER
    // New Getter and Setter for Product Name
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    // NEW: Getter and Setter for Target Total Weight
    public Double getTargetTotalWeight() { return targetTotalWeight; }
    public void setTargetTotalWeight(Double targetTotalWeight) { this.targetTotalWeight = targetTotalWeight; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getBlendingNumber() { return blendingNumber; }
    public void setBlendingNumber(String blendingNumber) { this.blendingNumber = blendingNumber; }

    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public String getFinishedGoodNumber() { return finishedGoodNumber; }
    public void setFinishedGoodNumber(String finishedGoodNumber) { this.finishedGoodNumber = finishedGoodNumber; }

    public String getTcNumber() { return tcNumber; }
    public void setTcNumber(String tcNumber) { this.tcNumber = tcNumber; }

    public boolean isTcApproved() { return tcApproved; }
    public void setTcApproved(boolean tcApproved) { this.tcApproved = tcApproved; }

    public LocalDate getBlendingDate() { return blendingDate; }
    public void setBlendingDate(LocalDate blendingDate) { this.blendingDate = blendingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}