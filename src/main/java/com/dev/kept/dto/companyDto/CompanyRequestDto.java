package com.dev.kept.dto.companyDto;

public class CompanyRequestDto {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CompanyRequestDto(String name) {
        this.name = name;
    }
}
