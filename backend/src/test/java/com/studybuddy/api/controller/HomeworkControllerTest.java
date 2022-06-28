package com.studybuddy.api.controller;

import static org.mockito.Mockito.when;

import com.studybuddy.api.service.HomeworkService;

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

@ContextConfiguration(classes = {HomeworkController.class})
@ExtendWith(SpringExtension.class)
class HomeworkControllerTest {
    @Autowired
    private HomeworkController homeworkController;

    @MockBean
    private HomeworkService homeworkService;

    /**
     * Method under test: {@link HomeworkController#getHomeworkCollection()}
     */
    @Test
    void testGetHomeworkCollection() throws Exception {
        when(this.homeworkService.findCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homework");
        MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link HomeworkController#getHomeworkCollection()}
     */
    @Test
    void testGetHomeworkCollection2() throws Exception {
        when(this.homeworkService.findCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/homework");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.homeworkController).build().perform(getResult).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }
}

