package com.culturebreathexhibitionsback.controller;

import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.service.impl.ExhibitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibitions")
public class ExhibitionController {

    private final ExhibitionServiceImpl exhibitionService;

    @GetMapping
    public List<ExhibitionDto> getAllExhibitions() {
        return exhibitionService.getAllExhibitions();
    }

    @GetMapping("/{exhibitionId}")
    public ExhibitionDto getExhibitionById(@PathVariable UUID exhibitionId) {
        return exhibitionService.findExhibitionById(exhibitionId);
    }

    @GetMapping("/{exhibitionTopic}")
    public ExhibitionDto getExhibitionByName(@PathVariable String exhibitionTopic) {
        return exhibitionService.findExhibitionByName(exhibitionTopic);
    }
}
