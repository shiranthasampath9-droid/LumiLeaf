package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.GradeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeTransactionRepository extends JpaRepository<GradeTransaction, Long> {
}