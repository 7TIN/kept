package com.dev.kept.Service;

import java.util.List;

import com.dev.kept.dto.CompanyRequestDto;
import com.dev.kept.dto.CompanyResponseDto;

public interface CompanyService {
    CompanyResponseDto addCompany(CompanyRequestDto requestDto);
    List<CompanyResponseDto> getAllCompanies();
    CompanyResponseDto getCompanyById(Long id);
}
