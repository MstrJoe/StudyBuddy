package com.studybuddy.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class HomeworkDto {
    private String name;
    private Date deadline;
    private String description;
    private String link;
}

