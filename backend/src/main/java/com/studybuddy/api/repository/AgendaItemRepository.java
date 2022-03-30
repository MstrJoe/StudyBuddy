package com.studybuddy.api.repository;

import com.studybuddy.api.entity.AgendaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaItemRepository extends JpaRepository<AgendaItem, Long> {
}
