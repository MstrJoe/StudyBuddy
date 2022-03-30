package com.studybuddy.api.payload;

import lombok.Data;

import java.util.Date;

import com.studybuddy.api.entity.User;

@Data
public class UserMeResponseDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private RoleResponseDto role;
    private Date createdAt;
    private Date updatedAt;

    public UserMeResponseDto(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
        this.setRole(new RoleResponseDto(user.getRole()));
    }
}
