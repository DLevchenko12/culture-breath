package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.dto.mapper.ExhibitionMapper;
import com.culturebreathexhibitionsback.exception.ResourceNotFoundException;
import com.culturebreathexhibitionsback.model.Exhibition;
import com.culturebreathexhibitionsback.repository.ExhibitionRepository;
import com.culturebreathexhibitionsback.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    private final ExhibitionMapper exhibitionMapper;

    @Override
    public List<ExhibitionDto> getAllExhibitions() {
        return exhibitionRepository.findAll()
                .stream()
                .map(exhibitionMapper::exhibitionToExhibitionDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExhibitionDto createExhibition(ExhibitionDto exhibitionDto) {
        Exhibition exhibition = exhibitionRepository.save(exhibitionMapper.exhibitionDtoToExhibition(exhibitionDto));
        return exhibitionMapper.exhibitionToExhibitionDto(exhibition);
    }

    @Override
    public ExhibitionDto updateExhibition(UUID exhibitionId, ExhibitionDto exhibitionDto) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(
                () -> {
                    log.error("Exception was throw in 'updateExhibition' operation");
                    throw new ResourceNotFoundException("Exhibition with id: " + exhibitionId);
                });
        exhibitionMapper.updateExhibitionFromExhibitionDto(exhibitionDto, exhibition);
        exhibitionRepository.save(exhibition);

        return exhibitionMapper.exhibitionToExhibitionDto(exhibition);

    }

    @Override
    public void deleteExhibitionById(UUID exhibitionId) {
        exhibitionRepository.findById(exhibitionId).ifPresentOrElse(exhibitionRepository::delete,
                () -> {
                    log.error("Exception was thrown in 'deleteExhibitionById' operation");
                    throw new ResourceNotFoundException("Exhibition with id: " + exhibitionId);
                });
    }

    @Override
    public ExhibitionDto findExhibitionById(UUID exhibitionId) {
        return exhibitionRepository.findById(exhibitionId).map(exhibitionMapper::exhibitionToExhibitionDto)
                .orElseThrow(() -> {
                    log.error("Exception was thrown in 'findExhibitionById' operation");
                    throw new ResourceNotFoundException("Exhibition with id: " + exhibitionId);
                });
    }

    @Override
    public ExhibitionDto findExhibitionByName(String exhibitionTopic) {
        return exhibitionRepository.findExhibitionByExhibitionTopic(exhibitionTopic).map(exhibitionMapper::exhibitionToExhibitionDto)
                .orElseThrow(() -> {
                    log.error("Exception was thrown in 'findExhibitionByExhibitionTopic' operation");
                    throw new ResourceNotFoundException("Exhibition with name: " + exhibitionTopic);
                });
    }
}
