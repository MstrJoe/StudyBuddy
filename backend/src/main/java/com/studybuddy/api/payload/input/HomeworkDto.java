package com.studybuddy.api.payload.input;

import lombok.Data;

import java.util.Date;

@Data
public class HomeworkDto {
    private String name;
    private Date deadline;
    private String description;
    private String link;
}
