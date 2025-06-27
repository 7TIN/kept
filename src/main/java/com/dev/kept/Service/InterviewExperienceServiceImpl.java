package com.dev.kept.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.kept.Beans.Company;
import com.dev.kept.Beans.InterviewExperience;
import com.dev.kept.Beans.InterviewQuestion;
import com.dev.kept.Repository.CompanyRepository;
import com.dev.kept.Repository.InterviewExperienceRepository;
import com.dev.kept.dto.InterviewExperienceRequestDto;
import com.dev.kept.dto.InterviewExperienceResponseDto;
import com.dev.kept.dto.interviewQueDto.InterviewQuestionResponseDto;
import com.dev.kept.enums.ExperienceType;

@Service
public class InterviewExperienceServiceImpl implements InterviewExperienceService {

    private final CompanyRepository companyRepo;
    private final InterviewExperienceRepository expRepo;

    public InterviewExperienceServiceImpl(CompanyRepository companyRepo,
                                          InterviewExperienceRepository expRepo) {
        this.companyRepo = companyRepo;
        this.expRepo     = expRepo;
    }

    @Override
    public InterviewExperienceResponseDto addExperience(InterviewExperienceRequestDto dto) {
        Company company = companyRepo.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        InterviewExperience exp = new InterviewExperience();
        exp.setTitle(dto.getTitle());
        exp.setPosition(dto.getPosition());
        exp.setExperienceType(ExperienceType.valueOf(dto.getExperienceType()));
        exp.setSummary(dto.getSummary());
        exp.setCompany(company);

        // map questions
        dto.getQuestions().forEach(q -> {
            InterviewQuestion iq = new InterviewQuestion();
            iq.setQuestionText(q.getQuestion());
            iq.setType(q.getType());
            iq.setSection(q.getSection());
            iq.setExperience(exp);
            exp.getQuestions().add(iq);
        });


        InterviewExperience saved = expRepo.save(exp);
        return toDto(saved);
    }

    @Override
    public List<InterviewExperienceResponseDto> getByCompany(Long companyId) {
        return expRepo.findByCompanyId(companyId)
                      .stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }

    /* ---------- mapper ---------- */
    private InterviewExperienceResponseDto toDto(InterviewExperience exp) {
        InterviewExperienceResponseDto dto = new InterviewExperienceResponseDto();
        dto.setId(exp.getId());
        dto.setTitle(exp.getTitle());
        dto.setPosition(exp.getPosition());
        dto.setExperienceType(exp.getExperienceType().name());
        dto.setSummary(exp.getSummary());
        dto.setCompanyName(exp.getCompany().getName());

        dto.setQuestions(
            exp.getQuestions().stream().map(q -> {
                InterviewQuestionResponseDto qdto = new InterviewQuestionResponseDto();
                qdto.setId(q.getId());
                qdto.setQuestion(q.getQuestionText());
                qdto.setType(q.getType());
                qdto.setSection(q.getSection());
                return qdto;
            }).collect(Collectors.toList())
        );
        return dto;
    }
}
