package com.studybuddy.api.controller;

import static org.mockito.Mockito.when;

import com.studybuddy.api.payload.responses.SubjectResponseDto;
import com.studybuddy.api.service.SubjectService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SubjectController.class})
@ExtendWith(SpringExtension.class)
class SubjectControllerTest {
    @Autowired
    private SubjectController subjectController;

    @MockBean
    private SubjectService subjectService;

    /**
     * Method under test: {@link SubjectController#getSubjectCollection()}
     */
    @Test
    void testGetSubjectCollection() throws Exception {
        when(this.subjectService.findCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subject");
        MockMvcBuilders.standaloneSetup(this.subjectController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SubjectController#getSubjectCollection()}
     */
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
}

