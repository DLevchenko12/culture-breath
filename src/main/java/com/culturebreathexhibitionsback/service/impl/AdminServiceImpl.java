package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.dto.AdminDto;
import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.dto.mapper.AdminMapper;
import com.culturebreathexhibitionsback.dto.mapper.ExhibitionMapper;
import com.culturebreathexhibitionsback.exception.UserAlreadyExistsException;
import com.culturebreathexhibitionsback.model.Admin;
import com.culturebreathexhibitionsback.model.Exhibition;
import com.culturebreathexhibitionsback.repository.AdminRepository;
import com.culturebreathexhibitionsback.repository.ExhibitionRepository;
import com.culturebreathexhibitionsback.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String MESSAGE = "Unable to find Admin with id: ";
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionMapper exhibitionMapper;


    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::adminToAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto getAdminById(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException(MESSAGE + adminId));
        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        if (adminRepository.existsById(adminDto.getId())) {
            throw new UserAlreadyExistsException(adminDto.getId());
        } else {
            Admin admin = adminRepository.save(adminMapper.adminDtoToAdmin(adminDto));
            return adminMapper.adminToAdminDto(admin);
        }
    }

    @Override
    public AdminDto updateAdminById(AdminDto adminDto, UUID adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new EntityNotFoundException(MESSAGE + adminId));
        adminMapper.updateAdminFromAdminDto(adminDto, admin);
        adminRepository.save(admin);

        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    public void deleteAdminById(UUID adminId) {
        if (adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
        } else {
            throw new EntityNotFoundException(MESSAGE + adminId);
        }
    }

    @Override
    public List<ExhibitionDto> getExhibitionsList() {
        return exhibitionRepository.findAll()
                .stream()
                .map(exhibitionMapper::exhibitionToExhibitionDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExhibitionDto createExhibition(ExhibitionDto exhibitionDto) {
        Exhibition exhibition = exhibitionMapper.exhibitionDtoToExhibition(exhibitionDto);
        exhibitionRepository.save(exhibition);

        return exhibitionMapper.exhibitionToExhibitionDto(exhibition);

    }

    @Override
    public ExhibitionDto updateExhibitionById(UUID exhibitionId, ExhibitionDto exhibitionDto) {


        return null;
    }

    @Override
    public void cancelExhibition(UUID exhibitionId){
        Exhibition exhibition = exhibitionRepository.findExhibitionById(exhibitionId).orElseThrow(
                () -> new EntityNotFoundException("Unable to find Appointment with id: " + exhibitionId));
        exhibitionRepository.delete(exhibition);
    }
}
