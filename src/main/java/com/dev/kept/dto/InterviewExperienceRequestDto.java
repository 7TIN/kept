package com.dev.kept.dto;

import java.util.List;

import com.dev.kept.dto.interviewQueDto.InterviewQuestionRequestDto;

public class InterviewExperienceRequestDto {

    private String title;
    private String position;
    private String experienceType;
    private String summary;
    private Long companyId;
    private String interviewDate;
    private List<InterviewQuestionRequestDto> questions;
    
    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<InterviewQuestionRequestDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestionRequestDto> questions) {
        this.questions = questions;
    }
}