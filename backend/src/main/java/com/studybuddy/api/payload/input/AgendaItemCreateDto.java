package com.studybuddy.api.payload.input;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Optional;

@Data
public class AgendaItemCreateDto {

    private Optional<Long> homeworkId;
    @NotBlank
    @Size (min = 2, max = 255 )
    private String title;
    @FutureOrPresent
    private Date moment;
    @NotBlank
    @Size (min = 2, max = 1000 )
    private String description;
    @URL
    private String link;
}