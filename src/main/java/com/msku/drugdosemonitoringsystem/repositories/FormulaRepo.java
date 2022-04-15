package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepo extends JpaRepository<Formula,Long> {
}
