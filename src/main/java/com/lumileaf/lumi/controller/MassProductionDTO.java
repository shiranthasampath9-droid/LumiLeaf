package com.lumileaf.lumi.controller;

import java.time.LocalDate;

public class MassProductionDTO {
    public double getEffectiveEstimate() {
        return (estimatedAmount != null && estimatedAmount > 0)
                ? estimatedAmount
                : (estimateMadeTea != null ? estimateMadeTea : 0.0);
    }

    public String getExcessLossLabel() {
        double actual = (actualMadeTea != null) ? actualMadeTea : 0.0;
        double diff = actual - getEffectiveEstimate();
        if (diff > 0) return "Excess";
        if (diff < 0) return "Loss";
        return "—";
    }

    public double getExcessLossAmount() {
        double actual = (actualMadeTea != null) ? actualMadeTea : 0.0;
        return Math.abs(actual - getEffectiveEstimate());
    }

    public String getFormattedExcessLoss() {
        return String.format("%.2f", getExcessLossAmount());
    }

    public String getExcessLossPercent() {
        double est = getEffectiveEstimate();
        if (est <= 0) return "0.0";
        return String.format("%.1f", (getExcessLossAmount() / est) * 100);
    }
    private LocalDate date;
    private Double netQuantityReceived;
    private Double estimateMadeTea;
    private Double actualMadeTea;
    private Double weight;
    // ✅ FIX #2: Add estimatedAmount field
    private Double estimatedAmount;

    /**
     * Updated constructor to handle database return types.
     * Use 'Object' to allow for BigDecimal, Long, or Double from SQL results.
     */
    public MassProductionDTO(LocalDate date, Object netQuantityReceivedRaw, Double actualMadeTea) {
        this.date = date;

        // Robust conversion logic to handle SQL aggregate function results
        if (netQuantityReceivedRaw instanceof Number) {
            this.netQuantityReceived = ((Number) netQuantityReceivedRaw).doubleValue();
        } else {
            this.netQuantityReceived = 0.0;
        }

        // Logic for tea manufacturing calculation (21.5%)
        this.estimateMadeTea = this.netQuantityReceived * 0.215;
        this.actualMadeTea = (actualMadeTea != null) ? actualMadeTea : 0.0;
        // ✅ FIX #2: Initialize estimatedAmount with default calculation
        this.estimatedAmount = this.estimateMadeTea;
    }

    // Logic for Dashboard Status
    public String getStatus() {
        if (actualMadeTea == null || actualMadeTea <= 0) return "PENDING";

        // Threshold check (90% of estimate or estimated amount)
        double threshold = (estimatedAmount != null && estimatedAmount > 0 ? estimatedAmount : estimateMadeTea) * 0.90;
        return (actualMadeTea < threshold) ? "LOW" : "NORMAL";
    }

    // Formatter methods for Thymeleaf display
    public String getFormattedNet() {
        return String.format("%.2f", netQuantityReceived != null ? netQuantityReceived : 0.0);
    }

    public String getFormattedEstimate() {
        // ✅ FIX #2: Use estimatedAmount if set, otherwise use calculated estimate
        double displayEstimate = (estimatedAmount != null && estimatedAmount > 0) ? estimatedAmount : estimateMadeTea;
        return String.format("%.2f", displayEstimate);
    }

    public String getFormattedActual() {
        return String.format("%.2f", actualMadeTea != null ? actualMadeTea : 0.0);
    }

    // Standard Getters (Required for Thymeleaf dot notation)
    public LocalDate getDate() { return date; }
    public Double getNetQuantityReceived() { return netQuantityReceived; }
    public Double getEstimateMadeTea() { return estimateMadeTea; }
    public Double getActualMadeTea() { return actualMadeTea; }
    public double getTotalWeight() {
        return netQuantityReceived != null ? netQuantityReceived : 0.0;
    }
    // ✅ FIX #2: Getter and setter for estimatedAmount
    public Double getEstimatedAmount() { return estimatedAmount; }
    public void setEstimatedAmount(Double estimatedAmount) { this.estimatedAmount = estimatedAmount; }
}