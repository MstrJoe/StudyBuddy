package com.studybuddy.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.studybuddy.api.entity.AgendaItem;
import com.studybuddy.api.entity.Role;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.responses.AgendaItemResponseDto;
import com.studybuddy.api.service.AgendaItemService;
import com.studybuddy.api.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
    void testGetAgendaItem4() throws Exception {
        when(this.agendaItemService.getItemById((Long) any())).thenThrow(
                new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/agendaitem/{id}", 123L);
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                getResult);
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
    void testGetAgendaItemCollection3() throws Exception {
        when(this.agendaItemService.getCollection()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/agendaitem");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.agendaItemController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

