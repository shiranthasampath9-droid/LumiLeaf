package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.Blending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BlendingRepository extends JpaRepository<Blending, Long> {

    Optional<Blending> findByFinishedGoodNumber(String finishedGoodNumber);

    Optional<Blending> findFirstByOrderByIdDesc();

    @Query("SELECT b FROM Blending b WHERE UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) ORDER BY b.id DESC")
    Optional<Blending> findFirstByGradeOrderByIdDesc(@Param("grade") String grade);

    /**
     * Sums only blends from fresh production for TODAY.
     */
    @Query("SELECT COALESCE(SUM(b.quantity), 0.0) FROM Blending b " +
            "WHERE UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) " +
            "AND b.batchNumber IS NOT NULL " +
            "AND b.batchNumber != 'FROM-REMNANTS' " +
            "AND b.blendingDate = CURRENT_DATE " +
            "AND UPPER(b.status) LIKE 'APPROV%'")
    Double sumFreshBlendedByGrade(@Param("grade") String grade);

    /**
     * Sums blends from remnants for ALL TIME.
     * Logic: Subtracting this from the BlendBalance history gives the "Remaining Remnants" card value.
     */
    @Query("SELECT COALESCE(SUM(b.quantity), 0.0) FROM Blending b " +
            "WHERE UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) " +
            "AND b.batchNumber = 'FROM-REMNANTS' " +
            "AND UPPER(b.status) LIKE 'APPROV%'")
    Double sumRemnantBlendedByGrade(@Param("grade") String grade);

    @Query("SELECT COALESCE(SUM(b.quantity), 0.0) FROM Blending b WHERE b.batchNumber = :batchNo " +
            "AND UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) " +
            "AND UPPER(b.status) LIKE 'APPROV%'")
    Double sumQuantityByBatchAndGrade(@Param("batchNo") String batchNo, @Param("grade") String grade);

    @Query("SELECT DISTINCT b.blendingNumber FROM Blending b WHERE UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) AND b.blendingNumber IS NOT NULL")
    List<String> findDistinctBlendingNumbersByGrade(@Param("grade") String grade);

    /**
     * Helper for selection UIs - Finds which Blend IDs still have tea associated with them.
     */
    @Query("SELECT DISTINCT b.batchNumber FROM Blending b WHERE UPPER(TRIM(b.grade)) = UPPER(TRIM(:grade)) AND b.batchNumber IS NOT NULL")
    List<String> findBlendIdsWithStockForGrade(@Param("grade") String grade);
    List<Blending> findByInvoiceNumber(String invoiceNumber);
    List<Blending> findAllByFinishedGoodNumber(String finishedGoodNumber);
}