package com.studybuddy.api.payload;

import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SubjectResponseDto {
    private long id;
    private String name;
    private List<SubjectHomeworkResponseDto> homework = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;

    public SubjectResponseDto(Subject subject) {
        this.setId(subject.getId());
        this.setName(subject.getName());
        this.setCreatedAt(subject.getCreatedAt());
        this.setUpdatedAt(subject.getUpdatedAt());

        List<SubjectHomeworkResponseDto> homeworkList = new ArrayList<>();

        for (Homework homework : subject.getHomework()) {
            homeworkList.add(new SubjectHomeworkResponseDto(homework));
        }

        this.setHomework(homeworkList);
    }
}
