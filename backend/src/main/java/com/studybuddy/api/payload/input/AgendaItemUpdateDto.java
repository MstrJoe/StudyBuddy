package com.studybuddy.api.payload.input;

import lombok.Data;

import java.util.Date;

@Data
public class AgendaItemUpdateDto {
    private String title;
    private Date moment;
    private String description;
    private String link;
}