package com.dev.kept.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.kept.Beans.InterviewExperience;
import com.dev.kept.dto.InterviewExperienceRequestDto;
import com.dev.kept.dto.InterviewExperienceResponseDto;

public interface InterviewExperienceService {
    InterviewExperienceResponseDto addExperience(InterviewExperienceRequestDto dto);
    List<InterviewExperienceResponseDto> getByCompany(Long companyId);

    // Page<InterviewExperience> findByCompanyId(Long companyId, Pageable pageable);
    // Page<InterviewExperience> findAll(Pageable pageable);
    Page<InterviewExperienceResponseDto> getRecent(int page, int size, String q, String type);
}
