package com.dev.kept.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.kept.Beans.InterviewQuestion;

public interface InterviewQuestionRepository
        extends JpaRepository<InterviewQuestion, Long> {

    List<InterviewQuestion> findByExperienceId(Long experienceId);
}
