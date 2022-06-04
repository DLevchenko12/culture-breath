package com.culturebreathexhibitionsback.service;

import com.culturebreathexhibitionsback.dto.AdminDto;
import com.culturebreathexhibitionsback.dto.ExhibitionDto;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<AdminDto> getAllAdmins();

    AdminDto getAdminById(UUID adminId);

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto updateAdminById(AdminDto adminDto, UUID adminId);

    void deleteAdminById(UUID adminId);

    List<ExhibitionDto> getExhibitionsList();

    ExhibitionDto createExhibition(ExhibitionDto exhibitionDto);

    ExhibitionDto updateExhibitionById(UUID exhibitionId, ExhibitionDto exhibitionDto);

    void cancelExhibition(UUID exhibitionId);
}
