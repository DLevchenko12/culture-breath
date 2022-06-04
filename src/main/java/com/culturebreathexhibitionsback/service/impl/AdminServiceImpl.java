package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.dto.AdminDto;
import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.dto.mapper.AdminMapper;
import com.culturebreathexhibitionsback.exception.ResourceNotFoundException;
import com.culturebreathexhibitionsback.exception.UserAlreadyExistsException;
import com.culturebreathexhibitionsback.model.Admin;
import com.culturebreathexhibitionsback.repository.AdminRepository;
import com.culturebreathexhibitionsback.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final ExhibitionServiceImpl exhibitionService;

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::adminToAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto getAdminById(UUID adminId) {
        return adminRepository.findById(adminId)
                .map(adminMapper::adminToAdminDto)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with id: " + adminId));
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        if (adminRepository.existsById(adminDto.getId())) {
            throw new UserAlreadyExistsException(adminDto.getId());
        }
        Admin admin = adminRepository.save(adminMapper.adminDtoToAdmin(adminDto));
        return adminMapper.adminToAdminDto(admin);

    }

    @Override
    public AdminDto updateAdminById(AdminDto adminDto, UUID adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Admin with id: " + adminId));
        adminMapper.updateAdminFromAdminDto(adminDto, admin);
        adminRepository.save(admin);

        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    public void deleteAdminById(UUID adminId) {
        adminRepository.findById(adminId).ifPresentOrElse(adminRepository::delete, () -> {
            log.error("Exception was thrown while deleting source");
            throw new ResourceNotFoundException("Admin with id: " + adminId);
        });
    }

    @Override
    public List<ExhibitionDto> getExhibitionsList() {
        return exhibitionService.getAllExhibitions();
    }

    @Override
    public ExhibitionDto createExhibition(ExhibitionDto exhibitionDto) {
        return exhibitionService.createExhibition(exhibitionDto);
    }

    @Override
    public ExhibitionDto updateExhibitionById(UUID exhibitionId, ExhibitionDto exhibitionDto) {
        return exhibitionService.updateExhibition(exhibitionId, exhibitionDto);
    }

    @Override
    public void cancelExhibition(UUID exhibitionId) {
        exhibitionService.deleteExhibitionById(exhibitionId);
    }

}
