package com.dev.kept.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.kept.Beans.Company;
import com.dev.kept.Repository.CompanyRepository;
import com.dev.kept.dto.companyDto.CompanyRequestDto;
import com.dev.kept.dto.companyDto.CompanyResponseDto;
import com.dev.kept.dto.companyDto.CompanyWithCountDto;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repo;

    public CompanyServiceImpl(CompanyRepository repo) {
        this.repo = repo;
    }

    @Override
    public CompanyResponseDto addCompany(CompanyRequestDto dto) {
        Company company = new Company();
        company.setName(dto.getName());

        Company saved = repo.save(company);

        return toDto(saved);
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        Company company = repo.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        return toDto(company);
    }

    private CompanyResponseDto toDto(Company company) {
        CompanyResponseDto dto = new CompanyResponseDto();
        dto.setId(company.getId());
        dto.setName(company.getName());

        return dto;
    }

    @Override
    public List<CompanyResponseDto> search(String q) {
        return repo
                .findByNameContainingIgnoreCaseOrderByNameAsc(q.trim())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDto addOrGet(String name) {
        return repo.findByNameIgnoreCase(name)
                .map(this::toDto)
                .orElseGet(() -> addCompany(new CompanyRequestDto(name)));
    }

    @Override
    public List<CompanyWithCountDto> getAllWithExperienceCount() {
        return repo.findAllWithExperienceCount();
    }
}
