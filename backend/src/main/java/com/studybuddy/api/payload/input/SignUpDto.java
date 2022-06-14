package com.studybuddy.api.payload.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 255)
    private String password;

    private Long roleId;
}
