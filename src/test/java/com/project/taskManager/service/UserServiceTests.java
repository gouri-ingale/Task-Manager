package com.project.taskManager.service;

import com.project.taskManager.entity.User;
import com.project.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }


    @Disabled //If i don't wanna run this test case
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "gouri",
            "shaym"
    })
    public void testAdd(String name){
        //User user = userRepository.findByUserName("gouri");
        //assertTrue(!user.getTaskEntries().isEmpty());

        //assertEquals(4,4);

        //assertNotNull(userRepository.findByUserName("gouri"));

        assertNotNull(userRepository.findByUserName(name),"not found");
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
        "1,1,2",
            "2,1,4",
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
