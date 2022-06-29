package com.studybuddy.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Role;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.AgendaItemCreateDto;
import com.studybuddy.api.payload.responses.AgendaItemResponseDto;
import com.studybuddy.api.service.AgendaItemService;
import com.studybuddy.api.service.UserService;

import java.security.Principal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {AgendaItemController.class})
@ExtendWith(SpringExtension.class)
class AgendaItemControllerTest {
    @Autowired
    private AgendaItemController agendaItemController;

    @MockBean
    private AgendaItemService agendaItemService;

    @MockBean
    private UserService userService;


    @Test
    void testDeleteAgendaItem() throws Exception {
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
        when(this.userService.getByPrincipal((java.security.Principal) any())).thenReturn(user);
        doNothing().when(this.agendaItemService).deleteForUser((User) any(), (Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/agendaitem/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDeleteAgendaItem2() throws Exception {
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
        when(this.userService.getByPrincipal((java.security.Principal) any())).thenReturn(user);
        doThrow(new Exception("An error occurred")).when(this.agendaItemService).deleteForUser((User) any(), (Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/agendaitem/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testGetAgendaItem() throws Exception {
        when(this.agendaItemService.getItemById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAgendaItem2() throws Exception {
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

        AgendaItem agendaItem = new AgendaItem();
        agendaItem.setCreatedBy(user);
        AgendaItemResponseDto agendaItemResponseDto = new AgendaItemResponseDto(agendaItem);
        when(this.agendaItemService.getItemById((Long) any())).thenReturn(agendaItemResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":0,\"title\":null,\"moment\":null,\"description\":null,\"link\":null," + "\"homework" + "\":null,\"createdBy\":{\"id\"" + ":123,\"name\":\"Name\"," + "\"username\":\"janedoe" + "\",\"email\":\"jane.doe@example.org\",\"role\":{\"id\":123," + "\"name\":\"Name" + "\",\"displayName\":\"Display Name\"},\"createdAt\":0," + "\"updatedAt\":0," + "\"avatar\":\"Avatar\"},\"subscribers\":[]," + "\"createdAt\":null," + "\"updatedAt" + "\":null}"));
    }


    @Test
    void testGetAgendaItem3() throws Exception {
        when(this.agendaItemService.getItemById((Long) any())).thenThrow(
                new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAgendaItemCollection() throws Exception {
        when(this.agendaItemService.getCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem");
        MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAgendaItemCollection2() throws Exception {
        when(this.agendaItemService.getCollection()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testSubscribe() throws Exception {
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
        when(this.userService.getByPrincipal((java.security.Principal) any())).thenReturn(user);

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

        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setAgendaItems(new HashSet<>());
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setDeadline(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        homework.setDescription("The characteristics of someone or something");
        homework.setId(123L);
        homework.setLink("Link");
        homework.setName("Name");
        homework.setSubject(subject);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        homework.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));

        AgendaItem agendaItem = new AgendaItem();
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setCreatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setCreatedBy(user1);
        agendaItem.setDescription("The characteristics of someone or something");
        agendaItem.setHomework(homework);
        agendaItem.setId(123L);
        agendaItem.setLink("Link");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setMoment(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        agendaItem.setSubscribers(new HashSet<>());
        agendaItem.setTitle("Dr");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agendaItem.setUpdatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.agendaItemService.subscribeForUser((User) any(), (Long) any())).thenReturn(agendaItem);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/agendaitem/{id}/subscribe", 123L);
        MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":123,\"title\":\"Dr\",\"moment\":0,\"description\":\"The characteristics of someone or something\",\"link\"" + ":\"Link\",\"homework\":{\"id\":123,\"name\":\"Name\",\"deadline\":0,\"description\":\"The characteristics of someone" + " or something\",\"link\":\"Link\",\"subject\":{\"id\":123,\"name\":\"Name\",\"createdAt\":0,\"updatedAt\":0},\"createdAt" + "\":0,\"updatedAt\":0},\"createdBy\":{\"id\":123,\"name\":\"Name\",\"username\":\"janedoe\",\"email\":\"jane.doe@example" + ".org\",\"role\":{\"id\":123,\"name\":\"Name\",\"displayName\":\"Display Name\"},\"createdAt\":0,\"updatedAt\":0,\"avatar" + "\":\"Avatar\"},\"subscribers\":[],\"createdAt\":0,\"updatedAt\":0}"));
    }

    @Test
    void testSubscribe2() throws Exception {
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
        when(this.userService.getByPrincipal((java.security.Principal) any())).thenReturn(user);
        when(this.agendaItemService.subscribeForUser((User) any(), (Long) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/agendaitem/{id}/subscribe", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

