package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.service.MessageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SomeMessageControllerTest {

    @Autowired
    private MessageService messageService;

    @Test
    void greetidng() {

        Iterable<Message> messages = messageService.findById(164);

        Assert.assertNotNull(messages);
    }
}