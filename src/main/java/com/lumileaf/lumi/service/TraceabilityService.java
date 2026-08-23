package com.lumileaf.lumi.service;

import com.lumileaf.lumi.model.*;
import com.lumileaf.lumi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lumileaf.lumi.service.ContributionService;
import java.util.*;

@Service
public class TraceabilityService {

    @Autowired private BlendingRepository blendingRepo;
    @Autowired private ProductionBatchRepository productionRepo;
    @Autowired private WaitingPointRepository waitingRepo;
    @Autowired private ContributionService contributionService;
    @Autowired private StockProductionRepository stockProductionRepo;


    public Map<String, Object> getProductHistory(String fgNumber) {
        Map<String, Object> history = new HashMap<>();

        List<Blending> blendLines = blendingRepo.findAllByFinishedGoodNumber(fgNumber);
        if (blendLines.isEmpty()) {
            throw new RuntimeException("Product Not Found");
        }
        history.put("blendLines", blendLines);
        history.put("blend", blendLines.get(0));

        List<ProductionBatch> batches = new ArrayList<>();
        Set<Long> seenBatchIds = new HashSet<>();
        for (Blending line : blendLines) {
            for (String lot : line.getBatchList()) {
                String trimmed = lot.trim();
                for (ProductionBatch batch : contributionService.resolveLotNumberToBatches(trimmed)) {
                    if (seenBatchIds.add(batch.getId())) {
                        batches.add(batch);
                    }
                }
            }
        }
        history.put("batches", batches);

        List<FarmerContribution> farmerContributions = contributionService.getLotContributions(batches);
        history.put("farmerContributions", farmerContributions);

        return history;
    }
}