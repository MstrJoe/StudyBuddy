package com.studybuddy.api.payload;

import lombok.Data;

import java.util.Date;

@Data
public class AgendaItemDto {
    private String title;
    private Date moment;
    private String description;
    private String link;
}