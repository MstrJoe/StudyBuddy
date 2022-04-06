package com.studybuddy.api.payload.input;

import java.util.Optional;

import lombok.Data;

@Data
public class UserUpdateDto {
    private Optional<String> name;
    private Optional<String> username;
    private Optional<String> email;
    private Optional<String> password;
}
