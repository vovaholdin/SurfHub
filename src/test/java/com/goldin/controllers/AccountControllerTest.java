package com.goldin.controllers;

import com.goldin.entity.User;
import com.goldin.repository.UserRepository;
import com.goldin.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.ui.Model;

import java.util.Optional;
class AccountControllerTest extends AbstractTestClass<AccountController> {

    public AccountControllerTest(){
        super(AccountController.class);
    }
    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setup() throws Exception {
        openmocks();
        SecurityContextHolder.setContext(securityContext);

    }

    @Test
    void testAccountIsHave() {
        User user = new User();
        user.setName("vova");
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn("vova");
        Mockito.when(userRepository.findByUsername("vova")).thenReturn(Optional.of(user));
        String view = accountController.account(model);
        Mockito.verify(model).addAttribute("user", user);
        assertEquals("/profile/account", view);
    }

    @Test
    void testAccountIsNotHave(){

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn("vova");
        Mockito.when(userRepository.findByUsername("vova")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> {
            accountController.account(model);
        });
    }

    @Test
    void testAccountWithMockMvc() throws Exception {
        // Этот тест УПАДЁТ с NullPointerException!
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk());
    }


}