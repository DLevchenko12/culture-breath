package com.culturebreathexhibitionsback.dto.mapper;

import com.culturebreathexhibitionsback.dto.AdminDto;
import com.culturebreathexhibitionsback.model.Admin;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingTarget;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdminMapper {
    Admin adminDtoToAdmin(AdminDto adminDto);

    AdminDto adminToAdminDto(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromAdminDto(AdminDto adminDto, @MappingTarget Admin admin);
}
