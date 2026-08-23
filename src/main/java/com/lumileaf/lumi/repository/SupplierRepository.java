package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Gets all unique section names added by QA
    @Query("SELECT DISTINCT s.section FROM Supplier s WHERE s.section IS NOT NULL")
    List<String> findDistinctSections();

    // Finds all suppliers belonging to a specific section
    List<Supplier> findBySection(String section);
    // Add this line to allow checking for duplicate IDs
    java.util.Optional<com.lumileaf.lumi.model.Supplier> findBySupplierId(String supplierId);
}