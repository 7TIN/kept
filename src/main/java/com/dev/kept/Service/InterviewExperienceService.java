package com.dev.kept.Service;

import java.util.List;

import com.dev.kept.dto.InterviewExperienceRequestDto;
import com.dev.kept.dto.InterviewExperienceResponseDto;

public interface InterviewExperienceService {
    InterviewExperienceResponseDto addExperience(InterviewExperienceRequestDto dto);
    List<InterviewExperienceResponseDto> getByCompany(Long companyId);
}
