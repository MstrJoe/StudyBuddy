package com.studybuddy.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.studybuddy.api.payload.input.UserUpdateDto;
import com.sun.security.auth.UserPrincipal;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;

class UserControllerTest {
    /**
     * Method under test:
     * {@link UserController#updateMe(java.security.Principal, UserUpdateDto, org.springframework.validation.BindingResult)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateMe() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.studybuddy.api.service.UserService.getByPrincipal(java.security.Principal)" because "this.userService" is null
        //       at com.studybuddy.api.controller.UserController.updateMe(UserController.java:35)
        //   In order to prevent updateMe(Principal, UserUpdateDto, BindingResult)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateMe(Principal, UserUpdateDto, BindingResult).
        //   See https://diff.blue/R013 to resolve this issue.

        UserController userController = new UserController();
        UserPrincipal principal = new UserPrincipal("principal");
        UserUpdateDto userUpdateDto = mock(UserUpdateDto.class);
        doNothing().when(userUpdateDto).setEmail((Optional<String>) any());
        doNothing().when(userUpdateDto).setName((Optional<String>) any());
        doNothing().when(userUpdateDto).setPassword((Optional<String>) any());
        doNothing().when(userUpdateDto).setUsername((Optional<String>) any());
        userUpdateDto.setEmail(Optional.of("42"));
        userUpdateDto.setName(Optional.of("42"));
        userUpdateDto.setPassword(Optional.of("42"));
        userUpdateDto.setUsername(Optional.of("42"));
        userController.updateMe(principal, userUpdateDto, new BindException(new BindException(new BindException(
                new BindException(new BindException(new BeanPropertyBindingResult("Target", "Object Name")))))));
    }

    /**
     * Method under test:
     * {@link UserController#updateMe(java.security.Principal, UserUpdateDto, org.springframework.validation.BindingResult)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateMe2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.validation.BindingResult.hasErrors()" because "bindingResult" is null
        //       at com.studybuddy.api.controller.UserController.updateMe(UserController.java:31)
        //   In order to prevent updateMe(Principal, UserUpdateDto, BindingResult)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateMe(Principal, UserUpdateDto, BindingResult).
        //   See https://diff.blue/R013 to resolve this issue.

        UserController userController = new UserController();
        UserPrincipal principal = new UserPrincipal("principal");
        UserUpdateDto userUpdateDto = mock(UserUpdateDto.class);
        doNothing().when(userUpdateDto).setEmail((Optional<String>) any());
        doNothing().when(userUpdateDto).setName((Optional<String>) any());
        doNothing().when(userUpdateDto).setPassword((Optional<String>) any());
        doNothing().when(userUpdateDto).setUsername((Optional<String>) any());
        userUpdateDto.setEmail(Optional.of("42"));
        userUpdateDto.setName(Optional.of("42"));
        userUpdateDto.setPassword(Optional.of("42"));
        userUpdateDto.setUsername(Optional.of("42"));
        userController.updateMe(principal, userUpdateDto, null);
    }
}

