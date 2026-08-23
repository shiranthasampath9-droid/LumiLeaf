package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.WitheringPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WitheringPointRepository extends JpaRepository<WitheringPoint, Long> {

    // Simple lookup by officer name
    List<WitheringPoint> findByWitheringOfficer(String witheringOfficer);

    // FIXED SYMBOL FOR PRODUCTION CONTROLLER:
    // Finds a single record matching the given Batch ID string
    Optional<WitheringPoint> findFirstByBatchId(String batchId);

    // FIXED SYMBOL FOR RECORD, PRODUCTION, AND QA CONTROLLERS:
    // Returns List<Object[]> exactly as legacy dashboards expect, grouped by section
    @Query("SELECT wp.batchId, SUM(wp.witheredWeight), wp.section " +
            "FROM WitheringPoint wp " +
            "WHERE wp.date = :targetDate " +
            "GROUP BY wp.batchId, wp.section")
    List<Object[]> findSummedWitheringByDate(@Param("targetDate") LocalDate targetDate);

    // Dynamic Format for our newly updated Withering Mobile UI
    @Query("SELECT wp.batchId as batchId, wp.section as section, " +
            "SUM(wp.intakeWeight) as totalIntakeWeight, SUM(wp.witheredWeight) as totalWitheredWeight, " +
            "MAX(wp.lotNumber) as lotNumber " +
            "FROM WitheringPoint wp " +
            "WHERE wp.date = :targetDate " +
            "GROUP BY wp.batchId, wp.section")
    List<Map<String, Object>> findSummedWitheringByBatchAndSectionAndDate(@Param("targetDate") LocalDate targetDate);
}