package com.lumileaf.lumi.repository;

import com.lumileaf.lumi.model.MassProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface MassProductionRepository extends JpaRepository<MassProduction, Long> {
    Optional<MassProduction> findByDate(LocalDate date);
}