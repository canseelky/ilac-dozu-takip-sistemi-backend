package com.msku.drugdosemonitoringsystem.repositories;


import com.msku.drugdosemonitoringsystem.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DrugRepo extends JpaRepository<Drug,Long> {
    List<Drug> findAllByDrugNameLike(String term);
    List<Drug> findAllByDrugNameContaining(String term);
    @Query(value = "SELECT * FROM drug d WHERE d.drug_name LIKE %:term% or d.active_ingredient LIKE %:term%",
    nativeQuery = true)
    List<Drug> findAllByDrugNameLikeL(@Param("term") String term);


}
