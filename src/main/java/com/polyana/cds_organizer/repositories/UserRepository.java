package com.polyana.cds_organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyana.cds_organizer.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
