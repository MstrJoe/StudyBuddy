package com.studybuddy.api.payload;

import com.studybuddy.api.entity.Role;

import lombok.Data;

@Data
public class RoleResponseDto {
    private long id;
    private String name;
    private String displayName;

    public RoleResponseDto(Role role) {
        this.setId(role.getId());
        this.setName(role.getName());
        this.setDisplayName(role.getDisplayName());
    }
}
