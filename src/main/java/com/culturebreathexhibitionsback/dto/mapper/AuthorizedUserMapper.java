package com.culturebreathexhibitionsback.dto.mapper;

import com.culturebreathexhibitionsback.dto.AuthorizedUserDto;
import com.culturebreathexhibitionsback.model.AuthorizedUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorizedUserMapper {
    AuthorizedUser authorizedUserDtoToAuthorizedUser(AuthorizedUserDto authorizedUserDto);

    AuthorizedUserDto authorizedUserToAuthorizedUserDto(AuthorizedUser authorizedUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthorizedUserFromDto(AuthorizedUserDto adminDto, @MappingTarget AuthorizedUser authorizedUser);
}
