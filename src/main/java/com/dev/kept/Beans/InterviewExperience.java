package com.dev.kept.Beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dev.kept.enums.ExperienceType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class InterviewExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String position;    
    private LocalDate interviewDate;  

    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;

    @Lob
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;           

    @OneToMany(mappedBy = "experience",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<InterviewQuestion> questions = new ArrayList<>();


    public InterviewExperience() {}


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


    public LocalDate getInterviewDate() {
        return interviewDate;
    }


    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }


    public ExperienceType getExperienceType() {
        return experienceType;
    }


    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }


    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Company getCompany() {
        return company;
    }


    public void setCompany(Company company) {
        this.company = company;
    }


    public List<InterviewQuestion> getQuestions() {
        return questions;
    }


    public void setQuestions(List<InterviewQuestion> questions) {
        this.questions = questions;
    }
}

    // Getters and Setters

    