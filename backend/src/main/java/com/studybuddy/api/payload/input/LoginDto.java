package com.studybuddy.api.payload.input;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    @Size(min = 6, max = 255 )
    private String password;
}
