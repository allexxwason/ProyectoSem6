package com.proyectosem6.repository;

import com.proyectosem6.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByName(String name);
    Page<EventEntity> findByVenue(String venue, Pageable pageable);
    Page<EventEntity> findByDate(String date, Pageable pageable);
    Page<EventEntity> findByVenueAndDate(String venue, String date, Pageable pageable);
}
