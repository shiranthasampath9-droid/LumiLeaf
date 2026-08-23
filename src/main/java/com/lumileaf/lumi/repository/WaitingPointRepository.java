package com.lumileaf.lumi.repository;

// ✅ FIX: MassProductionDTO actually lives in the "controller" package on disk
// (src/main/java/com/lumileaf/lumi/controller/MassProductionDTO.java), not "dto".
// Importing it from a nonexistent "dto" package was a compile error.
import com.lumileaf.lumi.controller.MassProductionDTO;
import com.lumileaf.lumi.model.WaitingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingPointRepository extends JpaRepository<WaitingPoint, Long> {

    List<WaitingPoint> findByDate(LocalDate date);

    // ✅ FIX: fully qualified constructor reference updated to match MassProductionDTO's
    // real package, and it now matches the (LocalDate, Double) constructor that was added
    // to MassProductionDTO specifically for this query — previously there was no constructor
    // with this signature at all, which fails Hibernate's query validation at startup.
    @Query("SELECT new com.lumileaf.lumi.controller.MassProductionDTO(w.date, SUM(w.weight), null) " +
            "FROM WaitingPoint w GROUP BY w.date")
    List<MassProductionDTO> aggregateWeightByDate();


    // Find only PENDING records (not finalized)
    List<WaitingPoint> findByStatusOrderByDateDesc(String status);

    // Find records by batch ID and status
    Optional<WaitingPoint> findByBatchIdAndStatus(String batchId, String status);

    // Find all pending records for a specific date
    List<WaitingPoint> findByStatusAndDateOrderByDateDesc(String status, LocalDate date);

    // Check if a batch ID is already finalized
    Long countByBatchIdAndStatus(String batchId, String status);

    // Existing methods
    List<WaitingPoint> findByBatchId(String batchId);
    Optional<WaitingPoint> findFirstByBatchId(String batchId);
}