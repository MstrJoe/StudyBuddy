package com.studybuddy.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.entity.Subject;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.responses.HomeworkWithSubjectResponseDto;
import com.studybuddy.api.service.HomeworkService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {HomeworkController.class})
@ExtendWith(SpringExtension.class)
class HomeworkControllerTest {
    @Autowired
    private HomeworkController homeworkController;

    @MockBean
    private HomeworkService homeworkService;


    @Test
    void testDeleteHomework() throws Exception {
        doNothing().when(this.homeworkService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/homework/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    void testDeleteHomework2() throws Exception {
        doThrow(new Exception("An error occurred")).when(this.homeworkService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/homework/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetHomework() throws Exception {
        when(this.homeworkService.findById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetHomework2() throws Exception {
        Subject subject = new Subject();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        subject.setHomework(new ArrayList<>());
        subject.setId(123L);
        subject.setName("Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        subject.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Homework homework = new Homework();
        homework.setSubject(subject);
        HomeworkWithSubjectResponseDto homeworkWithSubjectResponseDto = new HomeworkWithSubjectResponseDto(homework);
        when(this.homeworkService.findById((Long) any())).thenReturn(homeworkWithSubjectResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":0,\"name\":null,\"deadline\":null,\"description\":null,\"link\":null,\"subject\":{\"id\":123,\"name\":\"Name\"" + ",\"createdAt\":0,\"updatedAt\":0},\"createdAt\":null,\"updatedAt\":null}"));
    }


    @Test
    void testGetHomework3() throws Exception {
        when(this.homeworkService.findById((Long) any())).thenThrow(new Exception("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testGetHomeworkCollection() throws Exception {
        when(this.homeworkService.findCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework");
        MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetHomeworkCollection2() throws Exception {
        when(this.homeworkService.findCollection()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

}

