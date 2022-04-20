package com.studybuddy.api.payload.input;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class SubjectDto {
    @NotBlank
    @Max(value = 30)
    private String name;
}
