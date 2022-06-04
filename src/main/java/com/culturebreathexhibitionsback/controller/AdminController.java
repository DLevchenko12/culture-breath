package com.culturebreathexhibitionsback.controller;

import com.culturebreathexhibitionsback.dto.AdminDto;
import com.culturebreathexhibitionsback.dto.ExhibitionDto;
import com.culturebreathexhibitionsback.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<AdminDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{adminId}")
    public AdminDto getAdminById(@PathVariable UUID adminId) {
        return adminService.getAdminById(adminId);
    }

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminDto savedAdminDto = adminService.createAdmin(adminDto);
        return ResponseEntity.ok(savedAdminDto);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable UUID adminId, AdminDto adminDto) {
        AdminDto savedAdminDto = adminService.updateAdminById(adminDto, adminId);
        return ResponseEntity.ok(savedAdminDto);
    }

    @DeleteMapping("/{adminId}")
    public void deleteAdmin(@PathVariable UUID adminId) {
        adminService.deleteAdminById(adminId);
    }

    @GetMapping("/exhibitions")
    public List<ExhibitionDto> getAllExhibitions() {
        return adminService.getExhibitionsList();
    }

    @PostMapping("/exhibitions")
    public ResponseEntity<ExhibitionDto> createExhibition(@RequestBody ExhibitionDto exhibitionDto) {
        ExhibitionDto savedExhibitionDto = adminService.createExhibition(exhibitionDto);
        return ResponseEntity.ok(savedExhibitionDto);
    }

    @PutMapping("/exhibitions/{exhibitionId}")
    public ResponseEntity<ExhibitionDto> updateExhibition(@PathVariable UUID exhibitionId, @RequestBody ExhibitionDto exhibitionDto) {
        ExhibitionDto savedExhibitionDto = adminService.updateExhibitionById(exhibitionId, exhibitionDto);
        return ResponseEntity.ok(savedExhibitionDto);
    }

    @DeleteMapping("/exhibitions/{exhibitionId}")
    public void cancelExhibition(@PathVariable UUID exhibitionId) {
        adminService.cancelExhibition(exhibitionId);
    }
}
