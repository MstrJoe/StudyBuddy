package com.studybuddy.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.Role;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.UserUpdateDto;
import com.studybuddy.api.repository.UserRepository;
import com.sun.security.auth.UserPrincipal;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private FileUploadService fileUploadService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testGetByPrincipal() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
        assertSame(user, this.userService.getByPrincipal(new UserPrincipal("principal")));
        verify(this.userRepository).findByUsernameOrEmail((String) any(), (String) any());
    }

    @Test
    void testUpdate() {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Role role1 = new Role();
        role1.setDisplayName("Display Name");
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setAgendaItemSubscribed(new HashSet<>());
        user1.setAgendaItemsCreated(new ArrayList<>());
        user1.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRole(role1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail(Optional.of("42"));
        userUpdateDto.setName(Optional.of("42"));
        userUpdateDto.setPassword(Optional.of("42"));
        userUpdateDto.setUsername(Optional.of("42"));
        User actualUpdateResult = this.userService.update(user1, userUpdateDto);
        assertSame(user1, actualUpdateResult);
        assertEquals("42", actualUpdateResult.getUsername());
        assertEquals("secret", actualUpdateResult.getPassword());
        assertEquals("42", actualUpdateResult.getEmail());
        assertEquals("42", actualUpdateResult.getName());
        verify(this.userRepository).save((User) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void testUploadAvatar() throws Exception {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.fileUploadService.store((org.springframework.web.multipart.MultipartFile) any())).thenReturn("Store");
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8"));

        Role role1 = new Role();
        role1.setDisplayName("Display Name");
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setAgendaItemSubscribed(new HashSet<>());
        user1.setAgendaItemsCreated(new ArrayList<>());
        user1.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRole(role1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");
        User actualUploadAvatarResult = this.userService.uploadAvatar(file, user1);
        assertSame(user1, actualUploadAvatarResult);
        assertEquals("Store", actualUploadAvatarResult.getAvatar());
        verify(this.userRepository).save((User) any());
        verify(this.fileUploadService).store((org.springframework.web.multipart.MultipartFile) any());
    }


    @Test
    void testUploadAvatar2() throws Exception {
        Role role = new Role();
        role.setDisplayName("Display Name");
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setAgendaItemSubscribed(new HashSet<>());
        user.setAgendaItemsCreated(new ArrayList<>());
        user.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(role);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.fileUploadService.store((org.springframework.web.multipart.MultipartFile) any())).thenThrow(new Exception("An error occurred"));
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8"));

        Role role1 = new Role();
        role1.setDisplayName("Display Name");
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setAgendaItemSubscribed(new HashSet<>());
        user1.setAgendaItemsCreated(new ArrayList<>());
        user1.setAvatar("Avatar");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRole(role1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");
        assertThrows(Exception.class, () -> this.userService.uploadAvatar(file, user1));
        verify(this.fileUploadService).store((org.springframework.web.multipart.MultipartFile) any());
    }
}

