package com.studybuddy.api.payload.responses;

import java.util.Date;

import com.studybuddy.api.entity.User;

import lombok.Data;

@Data
public class UserResponseDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private RoleResponseDto role;
    private Date createdAt;
    private Date updatedAt;

    public UserResponseDto(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
        this.setRole(new RoleResponseDto(user.getRole()));
    }
}
