package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.StockProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockProductionRepository extends JpaRepository<StockProduction, Long> {

    // ── FIX (StockProductionRepository #6): Added so BlendingController can look up
    // a Stock Production lot by its lot number — required now that stock lot numbers
    // are guaranteed to equal the ProductionBatch lot number they were consolidated from.
    Optional<StockProduction> findByLotNumber(String lotNumber);
}