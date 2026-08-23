package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.BlendBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlendBalanceRepository extends JpaRepository<BlendBalance, Long> {

    Optional<BlendBalance> findByBlendId(String blendId);

    /**
     * FIX: Added back to resolve the "Cannot resolve method" error in BlendingController.
     * This finds IDs (like "Blend 33") that have stock for a specific grade.
     */
    @Query("SELECT b.blendId FROM BlendBalance b WHERE " +
            "(UPPER(:grade) = 'OP1' AND b.op1 > 0) OR " +
            "(UPPER(:grade) = 'OPA' AND b.opa > 0) OR " +
            "(UPPER(:grade) = 'BOP1' AND b.bop1 > 0) OR " +
            "(UPPER(:grade) = 'PEKOE' AND b.pekoe > 0) OR " +
            "(UPPER(:grade) = 'BOP' AND b.bop > 0) OR " +
            "(UPPER(:grade) = 'BOPF' AND b.bopf > 0) OR " +
            "(UPPER(:grade) = 'EB' AND b.eb > 0) OR " +
            "(UPPER(:grade) = 'FFSP' AND b.ffsp > 0) OR " +
            "(UPPER(:grade) = 'FFEXS' AND b.ffexs > 0) OR " +
            "(UPPER(:grade) = 'DUST' AND b.dust > 0) OR " +
            "(UPPER(:grade) = 'BM' AND b.bm > 0) OR " +
            "(UPPER(:grade) = 'BP' AND b.bp > 0) OR " +
            "(UPPER(:grade) = 'REFUSE' AND b.refusedTea > 0)")
    List<String> findBlendIdsWithStockForGrade(@Param("grade") String grade);

    // Summation methods for the QA Dashboard cards
    @Query("SELECT COALESCE(SUM(b.op1), 0.0) FROM BlendBalance b") Double sumAllOp1();
    @Query("SELECT COALESCE(SUM(b.opa), 0.0) FROM BlendBalance b") Double sumAllOpa();
    @Query("SELECT COALESCE(SUM(b.bop1), 0.0) FROM BlendBalance b") Double sumAllBop1();
    @Query("SELECT COALESCE(SUM(b.pekoe), 0.0) FROM BlendBalance b") Double sumAllPekoe();
    @Query("SELECT COALESCE(SUM(b.bop), 0.0) FROM BlendBalance b") Double sumAllBop();
    @Query("SELECT COALESCE(SUM(b.bopf), 0.0) FROM BlendBalance b") Double sumAllBopf();
    @Query("SELECT COALESCE(SUM(b.eb), 0.0) FROM BlendBalance b") Double sumAllEb();
    @Query("SELECT COALESCE(SUM(b.ffsp), 0.0) FROM BlendBalance b") Double sumAllFfsp();
    @Query("SELECT COALESCE(SUM(b.ffexs), 0.0) FROM BlendBalance b") Double sumAllFfexs();
    @Query("SELECT COALESCE(SUM(b.dust), 0.0) FROM BlendBalance b") Double sumAllDust();
    @Query("SELECT COALESCE(SUM(b.bm), 0.0) FROM BlendBalance b") Double sumAllBm();
    @Query("SELECT COALESCE(SUM(b.bp), 0.0) FROM BlendBalance b") Double sumAllBp();
    @Query("SELECT COALESCE(SUM(b.refusedTea), 0.0) FROM BlendBalance b") Double sumAllRefusedTea();
}