package com.culturebreathexhibitionsback.dto.mapper;

import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.model.Exhibition;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExhibitionMapper {
    Exhibition exhibitionDtoToExhibition(ExhibitionDto exhibitionDto);

    ExhibitionDto exhibitionToExhibitionDto(Exhibition exhibition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExhibitionFromExhibitionDto(ExhibitionDto exhibitionDto, @MappingTarget Exhibition exhibition);
}
