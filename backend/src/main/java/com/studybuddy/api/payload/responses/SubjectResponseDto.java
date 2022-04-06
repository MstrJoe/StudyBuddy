package com.studybuddy.api.payload.responses;

import com.studybuddy.api.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

        List<SubjectHomeworkResponseDto> homeworkList = subject.getHomework().stream()
                .map(item -> new SubjectHomeworkResponseDto(item)).collect(Collectors.toList());

        this.setHomework(homeworkList);
    }
}
