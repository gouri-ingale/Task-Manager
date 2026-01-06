package com.project.taskManager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
         emailService.sendEmail("gourisingale@gmail.com", "Testing Java Mail Sender", "Hi, app kaise hain ?");
    }
}
