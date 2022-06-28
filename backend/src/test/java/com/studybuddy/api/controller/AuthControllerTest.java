package com.studybuddy.api.controller;

import com.studybuddy.api.repository.RoleRepository;
import com.studybuddy.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

    /**
     * Method under test: {@link AuthController#signOut()}
     */
    @Test
    void testSignOut() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/signout");
        MockMvcBuilders.standaloneSetup(this.authController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("User signed-out successfully!."));
    }

    /**
     * Method under test: {@link AuthController#signOut()}
     */
    @Test
    void testSignOut2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/signout", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.authController).build().perform(requestBuilder).andExpect(
                MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1")).andExpect(
                MockMvcResultMatchers.content().string("User signed-out successfully!."));
    }
}

