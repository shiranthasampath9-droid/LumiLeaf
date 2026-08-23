package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long> {

    Optional<ProductionBatch> findByLotNumber(String lotNumber);

    // Return the most recent production batch for a lot number (safe when there are multiples)
    Optional<ProductionBatch> findTopByLotNumberOrderByRollingDateDesc(String lotNumber);

    // Returns all batches for a lot number if you need to handle multiple rows explicitly
    List<ProductionBatch> findAllByLotNumber(String lotNumber);

    Optional<ProductionBatch> findByLotNumberAndRollingDate(String lotNumber, LocalDate rollingDate);

    // NEW: Look up by the raw weighing / gate batch id (productionId)
    Optional<ProductionBatch> findByProductionId(String productionId);

    // NEW: Look up by productionId + rolling date (preferred when a date is available)
    Optional<ProductionBatch> findByProductionIdAndRollingDate(String productionId, LocalDate rollingDate);
    // Prefer exact match by raw gate / weighing batch id + rolling date

    // Useful when productionId is missing — return the latest row for the lot + date
    Optional<ProductionBatch> findTopByLotNumberAndRollingDateOrderByIdDesc(String lotNumber, LocalDate rollingDate);

    // If you ever need all matching rows
    List<ProductionBatch> findAllByLotNumberAndRollingDate(String lotNumber, LocalDate rollingDate);

    /**
     * NATIVE QUERIES
     * Uses the exact table name 'production_batches' and exact status 'APPROVED'
     * as verified by your SQL console.
     */

    @Query(value = "SELECT COALESCE(SUM(op1), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumOp1();

    @Query(value = "SELECT COALESCE(SUM(opa), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumOpa();

    @Query(value = "SELECT COALESCE(SUM(bop1), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumBop1();

    @Query(value = "SELECT COALESCE(SUM(pekoe), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumPekoe();

    @Query(value = "SELECT COALESCE(SUM(bop), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumBop();

    @Query(value = "SELECT COALESCE(SUM(bopf), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumBopf();

    @Query(value = "SELECT COALESCE(SUM(eb), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumEb();

    @Query(value = "SELECT COALESCE(SUM(ffsp), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumFfsp();

    @Query(value = "SELECT COALESCE(SUM(ffexs), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumFfexs();

    @Query(value = "SELECT COALESCE(SUM(dust), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumDust();

    @Query(value = "SELECT COALESCE(SUM(bm), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumBm();

    @Query(value = "SELECT COALESCE(SUM(bp), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumBp();

    @Query(value = "SELECT COALESCE(SUM(refused_tea), 0.0) FROM production_batches WHERE status = 'APPROVED'", nativeQuery = true)
    Double sumRefusedTea();
}