package com.studybuddy.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.studybuddy.api.payload.input.UserUpdateDto;
import com.sun.security.auth.UserPrincipal;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;

class UserControllerTest {

    @Test
    void testUpdateMe() {

        UserController userController = new UserController();
        UserPrincipal principal = new UserPrincipal("Name");
        UserUpdateDto userUpdateDto = mock(UserUpdateDto.class);
        doNothing().when(userUpdateDto).setEmail((Optional<String>) any());
        doNothing().when(userUpdateDto).setName((Optional<String>) any());
        doNothing().when(userUpdateDto).setPassword((Optional<String>) any());
        doNothing().when(userUpdateDto).setUsername((Optional<String>) any());
        userUpdateDto.setEmail(Optional.of("42"));
        userUpdateDto.setName(Optional.of("42"));
        userUpdateDto.setPassword(Optional.of("42"));
        userUpdateDto.setUsername(Optional.of("42"));
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        ResponseEntity<?> actualUpdateMeResult = userController.updateMe(principal, userUpdateDto, new BindException(new BindException(new BindException(new BindException(new BindException(beanPropertyBindingResult))))));
        assertTrue(actualUpdateMeResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualUpdateMeResult.getStatusCode());
        assertTrue(actualUpdateMeResult.getHeaders().isEmpty());
        verify(userUpdateDto).setEmail((Optional<String>) any());
        verify(userUpdateDto).setName((Optional<String>) any());
        verify(userUpdateDto).setPassword((Optional<String>) any());
        verify(userUpdateDto).setUsername((Optional<String>) any());
        verify(beanPropertyBindingResult).hasErrors();
        verify(beanPropertyBindingResult).getAllErrors();
    }

}

