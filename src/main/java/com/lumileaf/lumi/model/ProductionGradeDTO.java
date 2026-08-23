package com.lumileaf.lumi.model;

/**
 * Data Transfer Object for the QA Dashboard cards.
 * Updated to 'totalStock' to perfectly align with qa_dashboard.html.
 */
public class ProductionGradeDTO {

    private String name;
    private double freshStock;   // Matches ${grade.freshStock}
    private double remnantStock; // Matches ${grade.remnantStock}
    private double totalStock;   // Matches ${grade.totalStock} - FIXED naming mismatch

    // Constructor updated to match the field name and logic
    public ProductionGradeDTO(String name, double freshStock, double remnantStock, double totalStock) {
        this.name = name;
        this.freshStock = freshStock;
        this.remnantStock = remnantStock;
        this.totalStock = totalStock;
    }

    // Getters - Required by Thymeleaf/SpringEL
    public String getName() {
        return name;
    }

    public double getFreshStock() {
        return freshStock;
    }

    public double getRemnantStock() {
        return remnantStock;
    }

    public double getTotalStock() {
        return totalStock;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setFreshStock(double freshStock) {
        this.freshStock = freshStock;
    }

    public void setRemnantStock(double remnantStock) {
        this.remnantStock = remnantStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }
}