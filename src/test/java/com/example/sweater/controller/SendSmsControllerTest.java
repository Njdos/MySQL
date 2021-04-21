package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SendSmsControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void sendSms() {
        User messAuthId  = new User();



        long id  = 172;
        messAuthId.setId(id);

        Iterable<User> users = userService.findById(messAuthId.getId());

        Assert.assertNotNull(users);

    }
}