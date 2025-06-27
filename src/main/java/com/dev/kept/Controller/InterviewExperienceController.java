package com.dev.kept.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.kept.Service.InterviewExperienceService;
import com.dev.kept.dto.InterviewExperienceRequestDto;
import com.dev.kept.dto.InterviewExperienceResponseDto;

@RestController
@RequestMapping("/experiences")
public class InterviewExperienceController {

    private final InterviewExperienceService service;

    public InterviewExperienceController(InterviewExperienceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InterviewExperienceResponseDto>
        addExperience(@RequestBody InterviewExperienceRequestDto dto) {
        return ResponseEntity.ok(service.addExperience(dto));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<InterviewExperienceResponseDto>>
        getExperiencesByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(service.getByCompany(companyId));
    }
}
