package com.studybuddy.api.payload.input;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class HomeworkDto {
    @NotBlank
    @Size (min = 2, max = 255 )
    private String name;
    @Future
    private Date deadline;
    @NotBlank
    @Size(min = 2, max = 1000 )
    private String description;
    @URL
    private String link;
}
