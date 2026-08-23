package com.lumileaf.lumi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Supplier Images
    private String photoUrl;
    private String landPhotoUrl;

    // GPS Coordinates
    private Double latitude;
    private Double longitude;

    // Supplier Details
    private String name;
    private String supplierId;
    private String section;
    private String contact;

    // Soft Delete Status
    @Column(name = "status")
    private String status = "ACTIVE";

    public Supplier() {
    }

    public Supplier(Long id, String name, String supplierId, String section, String contact) {
        this.id = id;
        this.name = name;
        this.supplierId = supplierId;
        this.section = section;
        this.contact = contact;
        this.status = "ACTIVE";
    }

    // =========================
    // Getters & Setters
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLandPhotoUrl() {
        return landPhotoUrl;
    }

    public void setLandPhotoUrl(String landPhotoUrl) {
        this.landPhotoUrl = landPhotoUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}