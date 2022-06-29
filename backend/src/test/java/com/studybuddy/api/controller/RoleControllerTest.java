package com.studybuddy.api.controller;

import static org.mockito.Mockito.when;

import com.studybuddy.api.service.RoleService;

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

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleService roleService;


    @Test
    void testGetRoles() throws Exception {
        when(this.roleService.getCollection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/role");
        MockMvcBuilders.standaloneSetup(this.roleController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string("[]"));
    }

}

