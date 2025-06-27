package com.dev.kept.dto;

import java.util.List;

import com.dev.kept.dto.interviewQueDto.InterviewQuestionResponseDto;

public class InterviewExperienceResponseDto {

    private Long id;
    private String title;
    private String position;
    private String experienceType;
    private String summary;
    private String companyName;

    private List<InterviewQuestionResponseDto> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(String experienceType) {
        this.experienceType = experienceType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<InterviewQuestionResponseDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestionResponseDto> questions) {
        this.questions = questions;
    }
}
