package com.studybuddy.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studybuddy.api.entity.Homework;
import com.studybuddy.api.payload.input.HomeworkDto;
import com.studybuddy.api.payload.input.SubjectDto;
import com.studybuddy.api.payload.responses.SubjectHomeworkResponseDto;
import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.service.SubjectService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {SubjectController.class})
@ExtendWith(SpringExtension.class)
class SubjectControllerTest {
    @Autowired
    private SubjectController subjectController;

    @MockBean
    private SubjectService subjectService;

    @Test
    void testCreateSubject() throws Exception {
        when(this.subjectService.findCollection()).thenReturn(new ArrayList<>());

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testCreateSubject2() throws Exception {
        ArrayList<SubjectResponseDto> subjectResponseDtoList = new ArrayList<>();
        subjectResponseDtoList.add(new SubjectResponseDto());
        when(this.subjectService.findCollection()).thenReturn(subjectResponseDtoList);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "[{\"id\":0,\"name\":null,\"homework\":[],\"createdAt\":null,\"updatedAt\":null}]"));
    }


    @Test
    void testCreateSubject3() throws Exception {
        when(this.subjectService.findCollection()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testCreateSubject4() {
        SubjectController subjectController = new SubjectController();
        HomeworkDto homeworkDto = mock(HomeworkDto.class);
        doNothing().when(homeworkDto).setDeadline((Date) any());
        doNothing().when(homeworkDto).setDescription((String) any());
        doNothing().when(homeworkDto).setLink((String) any());
        doNothing().when(homeworkDto).setName((String) any());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        homeworkDto.setDeadline(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        homeworkDto.setDescription("The characteristics of someone or something");
        homeworkDto.setLink("Link");
        homeworkDto.setName("Name");
        assertThrows(ResponseStatusException.class, () -> subjectController.createSubject(123L, homeworkDto,
                new BindException(new BindException(new BindException(new BindException(new BindException(new BeanPropertyBindingResult("Target", "Object Name"))))))));
        verify(homeworkDto).setDeadline((Date) any());
        verify(homeworkDto).setDescription((String) any());
        verify(homeworkDto).setLink((String) any());
        verify(homeworkDto).setName((String) any());
    }


    @Test
    void testCreateSubject5() {

        SubjectController subjectController = new SubjectController();

        HomeworkDto homeworkDto = new HomeworkDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        homeworkDto.setDeadline(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        homeworkDto.setDescription("The characteristics of someone or something");
        homeworkDto.setLink("Link");
        homeworkDto.setName("Name");
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        when(beanPropertyBindingResult.hasErrors()).thenReturn(true);
        ResponseEntity<?> actualCreateSubjectResult = subjectController.createSubject(1L, homeworkDto, new BindException(new BindException(new BindException(new BindException(beanPropertyBindingResult)))));
        assertTrue(actualCreateSubjectResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualCreateSubjectResult.getStatusCode());
        assertTrue(actualCreateSubjectResult.getHeaders().isEmpty());
        verify(beanPropertyBindingResult).hasErrors();
        verify(beanPropertyBindingResult).getAllErrors();
    }

    @Test
    void testDeleteSubject() throws Exception {
        doNothing().when(this.subjectService).deleteSubject((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/subject/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    void testDeleteSubject2() throws Exception {
        doThrow(new Exception("An error occurred")).when(this.subjectService).deleteSubject((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/subject/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }



    @Test
    void testGetSubject() throws Exception {
        when(this.subjectService.findById((Long) any())).thenReturn(new SubjectResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":0,\"name\":null,\"homework\":[],\"createdAt\":null,\"updatedAt\":null}"));
    }

    @Test
    void testGetSubject2() throws Exception {
        when(this.subjectService.findById((Long) any())).thenThrow(new Exception("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetSubject4() throws Exception {
        ArrayList<SubjectHomeworkResponseDto> subjectHomeworkResponseDtoList = new ArrayList<>();
        subjectHomeworkResponseDtoList.add(new SubjectHomeworkResponseDto(new Homework()));
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createdAt = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        SubjectResponseDto subjectResponseDto = new SubjectResponseDto(123L, "?", subjectHomeworkResponseDtoList,
                createdAt, Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        when(this.subjectService.findById((Long) any())).thenReturn(subjectResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":123,\"name\":\"?\",\"homework\":[{\"id\":0,\"name\":null,\"deadline\":null,\"description\":null,\"link\":null," + "\"createdAt\":null,\"updatedAt\":null}],\"createdAt\":0,\"updatedAt\":0}"));
    }

    @Test
    void testGetSubjectCollection() throws Exception {
        when(this.subjectService.findCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject");
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetSubjectCollection2() throws Exception {
        ArrayList<SubjectResponseDto> subjectResponseDtoList = new ArrayList<>();
        subjectResponseDtoList.add(new SubjectResponseDto());
        when(this.subjectService.findCollection()).thenReturn(subjectResponseDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject");
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "[{\"id\":0,\"name\":null,\"homework\":[],\"createdAt\":null,\"updatedAt\":null}]"));
    }

    @Test
    void testGetSubjectCollection3() throws Exception {
        when(this.subjectService.findCollection()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testUpdateSubject() throws Exception {
        when(this.subjectService.putSubject((Long) any(), (SubjectDto) any())).thenReturn(new SubjectResponseDto());

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/subject/{id}", 123L).contentType(
                MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":0,\"name\":null,\"homework\":[],\"createdAt\":null,\"updatedAt\":null}"));
    }

    @Test
    void testUpdateSubject2() throws Exception {
        when(this.subjectService.putSubject((Long) any(), (SubjectDto) any())).thenReturn(new SubjectResponseDto());

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("?");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/subject/{id}", 123L).contentType(
                MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "[{\"codes\":[\"Size.subjectDto.name\",\"Size.name\",\"Size.java.lang.String\",\"Size\"],\"arguments\":[{\"codes\":" + "[\"subjectDto.name\",\"name\"],\"arguments\":null,\"defaultMessage\":\"name\",\"code\":\"name\"},255,3],\"defaultMessage" + "\":\"size must be between 3 and 255\",\"objectName\":\"subjectDto\",\"field\":\"name\",\"rejectedValue\":\"?\"," + "\"bindingFailure\":false,\"code\":\"Size\"}]"));
    }

    @Test
    void testUpdateSubject3() throws Exception {
        when(this.subjectService.putSubject((Long) any(), (SubjectDto) any())).thenThrow(new Exception("An error occurred"));

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/subject/{id}", 123L).contentType(
                MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testUpdateSubject4() throws Exception {
        ArrayList<SubjectHomeworkResponseDto> subjectHomeworkResponseDtoList = new ArrayList<>();
        subjectHomeworkResponseDtoList.add(new SubjectHomeworkResponseDto(new Homework()));
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createdAt = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        SubjectResponseDto subjectResponseDto = new SubjectResponseDto(123L, "?", subjectHomeworkResponseDtoList,
                createdAt, Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        when(this.subjectService.putSubject((Long) any(), (SubjectDto) any())).thenReturn(subjectResponseDto);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subjectDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/subject/{id}", 123L).contentType(
                MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "{\"id\":123,\"name\":\"?\",\"homework\":[{\"id\":0,\"name\":null,\"deadline\":null,\"description\":null,\"link\":null," + "\"createdAt\":null,\"updatedAt\":null}],\"createdAt\":0,\"updatedAt\":0}"));
    }
}

