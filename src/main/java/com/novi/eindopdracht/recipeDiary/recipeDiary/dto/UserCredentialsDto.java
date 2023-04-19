package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import java.util.List;

public class UserCredentialsDto {

    public Long userId;
    public String username;
    public List<String> roles;

    public UserCredentialsDto(Long userId, String username, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
