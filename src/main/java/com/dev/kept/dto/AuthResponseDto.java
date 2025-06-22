package com.dev.kept.dto;

public class AuthResponseDto {

    private String token;
    private String username;

    public AuthResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
