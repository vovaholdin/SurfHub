package com.goldin.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration

public abstract class AbstractTestClass<T>{

    protected MockMvc mockMvc;
    protected T controller;
    private final Class<T> mvcController;
    @Mock
    private SecurityContext securityContext;



    protected  AbstractTestClass(Class<T> mvcController){
        this.mvcController = mvcController;
    }


    @BeforeEach
    void setup() throws Exception {
        controller = mvcController.getDeclaredConstructor().newInstance();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    void openmocks(){
        MockitoAnnotations.openMocks(this);
    }
    //todo доделать тесты и понять почему нужен оригинал котроллера. Упростить код что бы опенмокс вызывалось в abstract

}
