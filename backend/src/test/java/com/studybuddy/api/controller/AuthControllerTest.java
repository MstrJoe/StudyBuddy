package com.studybuddy.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studybuddy.api.payload.input.LoginDto;
import com.studybuddy.api.payload.input.SignUpDto;
import com.studybuddy.api.repository.RoleRepository;
import com.studybuddy.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testAuthenticateUser() throws Exception {
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any())).thenReturn(
                new TestingAuthenticationToken("Principal", "Credentials"));

        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("iloveyou");
        loginDto.setUsernameOrEmail("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/signin").contentType(MediaType.APPLICATION_JSON).content(content);
        MockMvcBuilders.standaloneSetup(this.authController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("User signed-in successfully!."));
    }


    @Test
    void testAuthenticateUser2() throws Exception {
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any())).thenReturn(
                new TestingAuthenticationToken("Principal", "Credentials"));

        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("?");
        loginDto.setUsernameOrEmail("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/signin").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
                MockMvcResultMatchers.content().string(
                        "[{\"codes\":[\"Size.loginDto.password\",\"Size.password\",\"Size.java.lang.String\"," + "\"Size\"],\"arguments\":[{" + "\"codes\":[\"loginDto.password\",\"password\"],\"arguments\":null,\"defaultMessage\":\"password\",\"code\":\"password" + "\"},255,6],\"defaultMessage\":\"size must be between 6 and 255\",\"objectName\":\"loginDto\",\"field\":\"password" + "\",\"rejectedValue\":\"?\",\"bindingFailure\":false,\"code\":\"Size\"}]"));
    }

    @Test
    void testRegisterUser() throws Exception {
        when(this.userRepository.existsByEmail((String) any())).thenReturn(true);
        when(this.userRepository.existsByUsername((String) any())).thenReturn(true);

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail("jane.doe@example.org");
        signUpDto.setName("Name");
        signUpDto.setPassword("iloveyou");
        signUpDto.setRoleId(123L);
        signUpDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(signUpDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/signup").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("Username is already taken!"));
    }

    @Test
    void testRegisterUser2() throws Exception {
        when(this.userRepository.existsByEmail((String) any())).thenReturn(true);
        when(this.userRepository.existsByUsername((String) any())).thenReturn(false);

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail("jane.doe@example.org");
        signUpDto.setName("Name");
        signUpDto.setPassword("iloveyou");
        signUpDto.setRoleId(123L);
        signUpDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(signUpDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/signup").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("Email is already taken!"));
    }

    @Test
    void testSignOut() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/signout");
        MockMvcBuilders.standaloneSetup(this.authController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType("text" + "/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("User signed-out successfully!."));
    }

    @Test
    void testSignOut2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/signout", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.authController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(
                MockMvcResultMatchers.content().contentType("text" + "/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("User signed-out successfully!."));
    }
}

