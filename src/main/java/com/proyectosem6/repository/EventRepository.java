package com.proyectosem6.repository;

import com.proyectosem6.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByName(String name);
    Optional<EventEntity> findByNameIgnoreCase(String name);

    Page<EventEntity> findByVenueContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndDateGreaterThanEqual(
            String venue, String category, String date, Pageable pageable);
}
