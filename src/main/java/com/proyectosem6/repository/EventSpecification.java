package com.proyectosem6.repository;

import com.proyectosem6.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<EventEntity> filterBy(
            String city,
            String category,
            LocalDate startDate
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (city != null && !city.isEmpty()) {
                predicates.add(cb.equal(root.get("city"), city));
            }

            if (category != null && !category.isEmpty()) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
