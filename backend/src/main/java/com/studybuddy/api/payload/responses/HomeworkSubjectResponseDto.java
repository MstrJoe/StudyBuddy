package com.studybuddy.api.payload.responses;

import lombok.Data;

import java.util.Date;

import com.studybuddy.api.entity.Subject;

@Data
public class HomeworkSubjectResponseDto {
    private long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public HomeworkSubjectResponseDto(Subject subject) {
        this.setId(subject.getId());
        this.setName(subject.getName());
        this.setCreatedAt(subject.getCreatedAt());
        this.setUpdatedAt(subject.getUpdatedAt());
    }
}
