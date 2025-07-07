// src/main/java/com/dev/kept/dto/companyDto/CompanyWithCountDto.java
package com.dev.kept.dto.companyDto;

public class CompanyWithCountDto {
    private Long id;
    private String name;
    private long experienceCount;

    public CompanyWithCountDto(Long id, String name, long experienceCount) {
        this.id = id;
        this.name = name;
        this.experienceCount = experienceCount;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public long getExperienceCount() { return experienceCount; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperienceCount(long experienceCount) {
        this.experienceCount = experienceCount;
    }
}
