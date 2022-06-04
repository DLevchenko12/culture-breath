package com.culturebreathexhibitionsback.repository;

import com.culturebreathexhibitionsback.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExhibitionRepository extends JpaRepository<Exhibition, UUID> {
    Optional<Exhibition> findExhibitionByExhibitionTopic(String exhibitionTopic);
}