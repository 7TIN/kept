package com.dev.kept.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dev.kept.Beans.InterviewExperience;

public interface InterviewExperienceRepository
        extends JpaRepository<InterviewExperience, Long>,  JpaSpecificationExecutor<InterviewExperience> {

    List<InterviewExperience> findByCompanyId(Long companyId);
    // Page<InterviewExperience> findAllByOrder(Pageable pageable);
 
}