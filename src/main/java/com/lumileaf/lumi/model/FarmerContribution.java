package com.lumileaf.lumi.model;

public class FarmerContribution {
    private String supplierId;
    private String supplierName;
    private double weightKg;
    private double percent;

    public FarmerContribution(String supplierId, String supplierName, double weightKg, double percent) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.weightKg = weightKg;
        this.percent = percent;
    }

    public String getSupplierId() { return supplierId; }
    public String getSupplierName() { return supplierName; }
    public double getWeightKg() { return weightKg; }
    public double getPercent() { return percent; }
}