package com.proyectosem6.repository;

import com.proyectosem6.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<VenueEntity, Long> {
    Optional<VenueEntity> findByNameIgnoreCase(String name);
}
