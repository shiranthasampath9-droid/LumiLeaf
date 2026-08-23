package com.lumileaf.lumi.service;

import com.lumileaf.lumi.model.*;
import com.lumileaf.lumi.repository.*;
import com.lumileaf.lumi.util.BatchIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContributionService {

    @Autowired private WaitingPointRepository waitingRepo;
    @Autowired private ProductionBatchRepository productionRepo;
    @Autowired private StockProductionRepository stockProductionRepo;
    @Autowired private SupplierRepository supplierRepo;

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private Map<String, Double> aggregateWeightsByFarmer(List<String> gateBatchIds) {
        Map<String, Double> weightBySupplier = new LinkedHashMap<>();
        for (String gateBatchId : gateBatchIds) {
            List<WaitingPoint> rows = waitingRepo.findByBatchId(gateBatchId);
            if (rows == null) continue;
            for (WaitingPoint wp : rows) {
                if (wp.getSupplierId() == null || wp.getWeight() == null) continue;
                weightBySupplier.merge(wp.getSupplierId(), wp.getWeight(), Double::sum);
            }
        }
        return weightBySupplier;
    }

    private String resolveSupplierName(String supplierId) {
        Optional<Supplier> s = supplierRepo.findBySupplierId(supplierId);
        if (s.isPresent() && s.get().getName() != null && !s.get().getName().isBlank()) {
            return s.get().getName();
        }
        return "N/A";
    }

    private List<FarmerContribution> toContributionList(Map<String, Double> weightBySupplier) {
        double total = weightBySupplier.values().stream().mapToDouble(Double::doubleValue).sum();
        List<FarmerContribution> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : weightBySupplier.entrySet()) {
            String supplierId = entry.getKey();
            double weight = entry.getValue();
            double percent = total > 0 ? round((weight / total) * 100.0) : 0.0;
            result.add(new FarmerContribution(supplierId, resolveSupplierName(supplierId), round(weight), percent));
        }
        result.sort((a, b) -> Double.compare(b.getWeightKg(), a.getWeightKg()));
        return result;
    }

    public List<FarmerContribution> getBatchContributions(String gateBatchId) {
        if (gateBatchId == null || gateBatchId.isBlank()) return List.of();
        return toContributionList(aggregateWeightsByFarmer(List.of(gateBatchId)));
    }

    public List<FarmerContribution> getLotContributions(List<ProductionBatch> batches) {
        if (batches == null || batches.isEmpty()) return List.of();
        List<String> allGateBatchIds = new ArrayList<>();
        for (ProductionBatch batch : batches) {
            allGateBatchIds.addAll(BatchIdUtils.splitSubBatchIds(batch.getProductionId()));
        }
        return toContributionList(aggregateWeightsByFarmer(allGateBatchIds));
    }
    public List<FarmerContribution> getLotContributionsByActualMadeTea(List<ProductionBatch> batches) {
        if (batches == null || batches.isEmpty()) return List.of();

        List<FarmerContribution> greenLeafContributions = getLotContributions(batches);

        double actualMadeTeaTotal = 0.0;
        for (ProductionBatch batch : batches) {
            if (batch.getActualMadeTea() != null) {
                actualMadeTeaTotal += batch.getActualMadeTea();
            }
        }

        // Fallback: if Actual Made Tea hasn't been entered yet, show green-leaf-based
        // weights as the best available estimate until QA records the real figure.
        if (actualMadeTeaTotal <= 0) {
            return greenLeafContributions;
        }

        double finalTotal = actualMadeTeaTotal;
        List<FarmerContribution> scaled = new ArrayList<>();
        for (FarmerContribution fc : greenLeafContributions) {
            double scaledWeight = Math.round((fc.getPercent() / 100.0) * finalTotal * 100.0) / 100.0;
            scaled.add(new FarmerContribution(fc.getSupplierId(), fc.getSupplierName(), scaledWeight, fc.getPercent()));
        }
        return scaled;
    }
    public List<ProductionBatch> resolveLotNumberToBatches(String lotNumber) {
        if (lotNumber == null || lotNumber.isBlank() || "FROM-REMNANTS".equals(lotNumber)) {
            return List.of();
        }


        // 1) Try to find all production batches for the lot directly
        List<ProductionBatch> directList = productionRepo.findAllByLotNumber(lotNumber);
        if (directList != null && !directList.isEmpty()) {
            return directList;
        }

        // 2) If not in production batches, check if it's a consolidated stock lot and resolve sources
        Optional<StockProduction> stockLot = stockProductionRepo.findByLotNumber(lotNumber);
        if (stockLot.isPresent()) {
            List<ProductionBatch> resolved = new ArrayList<>();
            String[] sourceLots = stockLot.get().getLotNumber().split("\\s*\\+\\s*");
            for (String sourceLot : sourceLots) {
                String s = sourceLot.trim();
                List<ProductionBatch> subs = productionRepo.findAllByLotNumber(s);
                if (subs != null && !subs.isEmpty()) {
                    resolved.addAll(subs);
                }
            }
            return resolved;
        }

        return List.of();
    }
}