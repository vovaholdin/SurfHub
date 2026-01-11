package com.goldin.controllers;

import com.goldin.entity.User;
import com.goldin.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest extends AbstractTestClass<AuthController> {

    protected AuthControllerTest() {
        super(AuthController.class);
    }

    @Mock
    private UserService userService;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setup() {
        openmocks();
        AuthController authController = new AuthController(userService, userDetailsService);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testAuthAndLogin() throws Exception {
        mockMvc.perform(get("/registration")).andExpect(status().isOk());
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    void testRegistration() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("testname")
                .password("passwordEncoded")
                .authorities("ROLE_USER")
                .build();
        Mockito.when(userDetailsService.loadUserByUsername("testname")).thenReturn(userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        mockMvc.perform(post("/registration")
                .param("name", "testname")
                .param("password", "testpassword")
                .param("email", "testemail@gmail.com")
                .param("phone", "010101010")).andExpect(status().isFound());

        Mockito.verify(userDetailsService).loadUserByUsername("testname");
    }
}