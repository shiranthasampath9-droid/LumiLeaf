package com.lumileaf.lumi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class MassProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // One record per day
    private LocalDate date;

    private Double actualMadeTea; // Manually entered by QA

    // ✅ FIX #2: Add estimatedAmount field for manual editing
    private Double estimatedAmount; // Manually entered estimated amount

    // Getters and Setters
    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getActualMadeTea() { return actualMadeTea; }
    public void setActualMadeTea(Double actualMadeTea) { this.actualMadeTea = actualMadeTea; }

    // ✅ FIX #2: Getters and setters for estimatedAmount
    public Double getEstimatedAmount() { return estimatedAmount; }
    public void setEstimatedAmount(Double estimatedAmount) { this.estimatedAmount = estimatedAmount; }
}