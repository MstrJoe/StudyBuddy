package com.studybuddy.api.payload.responses;

import lombok.Data;

import java.util.Date;

import com.studybuddy.api.entity.Homework;

@Data
public class HomeworkWithSubjectResponseDto {
    private long id;
    private String name;
    private Date deadline;
    private String description;
    private String link;
    private HomeworkSubjectResponseDto subject;
    private Date createdAt;
    private Date updatedAt;

    public HomeworkWithSubjectResponseDto(Homework homework) {
        this.setId(homework.getId());
        this.setName(homework.getName());
        this.setDeadline(homework.getDeadline());
        this.setDescription(homework.getDescription());
        this.setLink(homework.getLink());
        this.setSubject(new HomeworkSubjectResponseDto(homework.getSubject()));
        this.setCreatedAt(homework.getCreatedAt());
        this.setUpdatedAt(homework.getUpdatedAt());
    }
}
