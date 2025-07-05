package com.dev.kept.Service;

import java.util.List;

import com.dev.kept.dto.companyDto.CompanyRequestDto;
import com.dev.kept.dto.companyDto.CompanyResponseDto;
import com.dev.kept.dto.companyDto.CompanyWithCountDto;

public interface CompanyService {
    CompanyResponseDto addCompany(CompanyRequestDto requestDto);
    List<CompanyResponseDto> getAllCompanies();
    CompanyResponseDto getCompanyById(Long id);
    List<CompanyResponseDto> search(String q);
    CompanyResponseDto addOrGet(String name);
    
    List<CompanyWithCountDto> getAllWithExperienceCount();
}
