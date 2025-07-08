package com.dev.kept.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        this.expRepo = expRepo;
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
        exp.setInterviewDate(LocalDate.parse(dto.getInterviewDate()));

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

    public Page<InterviewExperienceResponseDto> getAllExperiences(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("interviewDate").descending());
        return expRepo.findAll(pageable)
                .map(this::toDto);
    }

    // @Override
    // public Page<InterviewExperienceResponseDto> getRecent(int page, int size) {
    // Pageable pageable = PageRequest.of(
    // page,
    // size,
    // Sort.by("interviewDate").descending()
    // );

    // return expRepo.findAll(pageable)
    // .map(this::toDto);
    // }

    // @Override
    // public Page<InterviewExperienceResponseDto> getRecent(
    //         int page, int size, String keyword, String type, String companyName) {

    //     Pageable pageable = PageRequest.of(
    //             page, size, Sort.by("interviewDate").descending());

    //     // Start with an “all records” spec (conjunction)
    //     Specification<InterviewExperience> spec = (root, query, cb) -> cb.conjunction();

    //     // if (keyword != null && !keyword.isBlank()) {
    //     // spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("summary")),
    //     // "%" + keyword.toLowerCase() + "%"));
    //     // }

    //     // if (type != null && !type.isBlank()) {
    //     // spec = spec.and((root, query, cb) -> cb.equal(root.get("experienceType"),
    //     // type));
    //     // }

    //     if (keyword != null && !keyword.isBlank()) {
    //         spec = spec.and((root, q, cb) -> cb.or(
    //                 cb.like(cb.lower(root.get("summary")), "%" + keyword.trim().toLowerCase() + "%"),
    //                 cb.like(cb.lower(root.get("title")), "%" + keyword.trim().toLowerCase() + "%")));
    //     }

    //     /* --- type = TECHNICAL | HR | MR -------------------------------- */
    //     if (type != null && !type.isBlank()) {
    //         spec = spec.and((root, q, cb) -> cb.equal(root.get("experienceType"), type));
    //     }

    //     /* --- filter by company name (case‑insensitive) ----------------- */
    //     if (companyName != null && !companyName.isBlank()) {
    //         spec = spec.and((root, q, cb) -> {
    //             var join = root.join("company"); // interviewExperience → company
    //             return cb.equal(
    //                     cb.lower(join.get("name")),
    //                     companyName.trim().toLowerCase());
    //         });
    //     }

    //     return expRepo.findAll(spec, pageable)
    //             .map(this::toDto);
    // }


    @Override
public Page<InterviewExperienceResponseDto> getRecent(
        int page, int size, String keyword, String type, String position, String companyName) {

    Pageable pageable = PageRequest.of(
            page, size, Sort.by("interviewDate").descending());

    Specification<InterviewExperience> spec = (root, query, cb) -> cb.conjunction();

    if (keyword != null && !keyword.isBlank()) {
        spec = spec.and((root, q, cb) -> cb.or(
            cb.like(cb.lower(root.get("summary")), "%" + keyword.trim().toLowerCase() + "%"),
            cb.like(cb.lower(root.get("title")), "%" + keyword.trim().toLowerCase() + "%")
        ));
    }

    if (type != null && !type.isBlank()) {
        spec = spec.and((root, q, cb) ->
            cb.equal(root.get("experienceType"), type));
    }

    // Filter by position (case-insensitive, partial match)
    if (position != null && !position.isBlank()) {
        spec = spec.and((root, q, cb) ->
            cb.like(cb.lower(root.get("position")), "%" + position.trim().toLowerCase() + "%")
        );
    }

    if (companyName != null && !companyName.isBlank()) {
        spec = spec.and((root, q, cb) -> {
            var join = root.join("company");
            return cb.equal(
                    cb.lower(join.get("name")),
                    companyName.trim().toLowerCase());
        });
    }

    return expRepo.findAll(spec, pageable)
            .map(this::toDto);
}

    @Override
    public List<InterviewExperienceResponseDto> getByCompany(Long companyId) {
        return expRepo.findByCompanyId(companyId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private InterviewExperienceResponseDto toDto(InterviewExperience exp) {
        InterviewExperienceResponseDto dto = new InterviewExperienceResponseDto();
        dto.setId(exp.getId());
        dto.setTitle(exp.getTitle());
        dto.setPosition(exp.getPosition());
        dto.setExperienceType(exp.getExperienceType().name());
        dto.setSummary(exp.getSummary());
        dto.setCompanyName(exp.getCompany().getName());
        dto.setInterviewDate(exp.getInterviewDate() != null ? exp.getInterviewDate().toString() : null);

        dto.setQuestions(
                exp.getQuestions().stream().map(q -> {
                    InterviewQuestionResponseDto qdto = new InterviewQuestionResponseDto();
                    qdto.setId(q.getId());
                    qdto.setQuestion(q.getQuestionText());
                    qdto.setType(q.getType());
                    qdto.setSection(q.getSection());
                    return qdto;
                }).collect(Collectors.toList()));
        return dto;
    }
}
