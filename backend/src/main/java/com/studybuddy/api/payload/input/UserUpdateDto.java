package com.studybuddy.api.payload.input;

import java.util.Optional;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {
    @NotBlank (message = "Name can not be empty")
    private Optional<String> name;
    @NotBlank (message = "Username cannot be empty")
    private Optional<String> username;
    @Email
    private Optional<String> email;
    @NotBlank
    @Size(min = 6, max = 255 )
    private Optional<String> password;
}
