package com.studybuddy.api.payload.responses;

import com.studybuddy.api.entity.Homework;
import lombok.Data;

import java.util.Date;

@Data
public class SubjectHomeworkResponseDto {
    private long id;
    private String name;
    private Date deadline;
    private String description;
    private String link;
    private Date createdAt;
    private Date updatedAt;

    public SubjectHomeworkResponseDto(Homework homework) {
        this.setId(homework.getId());
        this.setName(homework.getName());
        this.setDeadline(homework.getDeadline());
        this.setDescription(homework.getDescription());
        this.setLink(homework.getLink());
        this.setCreatedAt(homework.getCreatedAt());
        this.setUpdatedAt(homework.getUpdatedAt());
    }
}
