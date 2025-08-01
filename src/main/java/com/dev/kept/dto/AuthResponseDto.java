package com.dev.kept.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDto {

    private String token;
    private String username;
    private String email;

        public AuthResponseDto() {
    }


    public AuthResponseDto(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }

    
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
