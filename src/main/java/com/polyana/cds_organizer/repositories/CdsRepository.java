package com.polyana.cds_organizer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyana.cds_organizer.models.Cds;

@Repository
public interface CdsRepository extends JpaRepository<Cds, Long> {
  
  List<Cds> findByUser_Id(Long id);

}
