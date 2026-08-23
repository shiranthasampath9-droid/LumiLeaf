package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.RollingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RollingPointRepository extends JpaRepository<RollingPoint, Long> {

    List<RollingPoint> findByRollingOfficer(String rollingOfficer);

    List<RollingPoint> findByEntryDate(LocalDate entryDate);

    Optional<RollingPoint> findFirstByBatchId(String batchId);

    // --- NEW: FETCH BY ROLLING DATE OR BATCH CORRELATIONS ---
    List<RollingPoint> findByRollingDate(LocalDate rollingDate);

    Optional<RollingPoint> findByBatchId(String batchId);

    // --- NEW: DRYING TAB LOGIC ---
    // Fetches only records where rolling is done but drying is still pending
    List<RollingPoint> findByDryingCompletedFalse();

    // --- NEW: QA DASHBOARD LOGIC ---
    // Fetches records that have been fully processed for the QA drying records view
    List<RollingPoint> findByDryingCompletedTrue();

    // --- NEW: AUTO-SELECT LATEST BATCH ---
    // Used to show the "Active Production Batch" in the mobile header
    @Query("SELECT r FROM RollingPoint r ORDER BY r.id DESC LIMIT 1")
    Optional<RollingPoint> findTopByOrderByIdDesc();
}