package com.culturebreathexhibitionsback.service;

import com.culturebreathexhibitionsback.dto.ExhibitionDto;

import java.util.List;
import java.util.UUID;

public interface ExhibitionService {

    List<ExhibitionDto> getAllExhibitions();

    ExhibitionDto createExhibition(ExhibitionDto exhibitionDto);

    ExhibitionDto updateExhibition(UUID exhibitionId, ExhibitionDto exhibitionDto);

    void deleteExhibitionById(UUID exhibitionId);

    ExhibitionDto findExhibitionById(UUID exhibitionId);

    ExhibitionDto findExhibitionByName(String exhibitionTopic);
}
