package com.studybuddy.api.payload.input;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AgendaItemUpdateDto {
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;
    private Date moment;
    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;
    @URL
    private String link;
}