package com.dev.kept.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.kept.Beans.InterviewExperience;

public interface InterviewExperienceRepository
        extends JpaRepository<InterviewExperience, Long> {

    List<InterviewExperience> findByCompanyId(Long companyId);
}