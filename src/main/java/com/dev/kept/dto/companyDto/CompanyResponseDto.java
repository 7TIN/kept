package com.dev.kept.dto.companyDto;

public class CompanyResponseDto {
    private Long id;
    private String name;
    // private String location;
    // private String industry;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
