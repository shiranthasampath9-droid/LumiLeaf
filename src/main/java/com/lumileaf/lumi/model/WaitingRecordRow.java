package com.lumileaf.lumi.model;

public class WaitingRecordRow {
    private final WaitingPoint record;
    private final Double contributionPercent;

    public WaitingRecordRow(WaitingPoint record, Double contributionPercent) {
        this.record = record;
        this.contributionPercent = contributionPercent;
    }

    public WaitingPoint getRecord() { return record; }
    public Double getContributionPercent() { return contributionPercent; }
}