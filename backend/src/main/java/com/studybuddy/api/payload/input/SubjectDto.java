package com.studybuddy.api.payload.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SubjectDto {
    @NotBlank ()
    @Size( min = 6, max = 255)
    private String name;
}
