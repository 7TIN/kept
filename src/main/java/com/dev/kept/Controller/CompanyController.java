package com.dev.kept.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.kept.Service.CompanyService;
import com.dev.kept.dto.companyDto.CompanyRequestDto;
import com.dev.kept.dto.companyDto.CompanyResponseDto;
import com.dev.kept.dto.companyDto.CompanyWithCountDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping(params = "q") // <‑‑ only when ?q present
    public ResponseEntity<List<CompanyResponseDto>> search(@RequestParam("q") String q) {
        return ResponseEntity.ok(service.search(q));
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto dto) {
        return ResponseEntity.ok(service.addCompany(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        return ResponseEntity.ok(service.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCompanyById(id));
    }

    @GetMapping("/by-count")
    public ResponseEntity<List<CompanyWithCountDto>> allWithCount() {
        return ResponseEntity.ok(service.getAllWithExperienceCount());
    }
}