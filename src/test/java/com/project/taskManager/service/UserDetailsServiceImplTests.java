package com.project.taskManager.service;

import com.project.taskManager.entity.User;
import com.project.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
@Disabled
@SpringBootTest
@ActiveProfiles("dev")
public class UserDetailsServiceImplTests {

    @InjectMocks //It creates an instance and injects in the @Mock annotation
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("gouri").password("fgdfg").roles(new ArrayList<>()).build()
        );
       UserDetails user =  userDetailsService.loadUserByUsername("gouri");
        Assertions.assertNotNull(user);
    }

}
