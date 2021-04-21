package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.service.MessageService;
import com.example.sweater.utils.validator.MessageValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddMessageControllerTest {

    @Autowired
    private MessageService messageService;

    @Mock
    private BindingResult bindingResult;

    @Autowired
    private MessageValidator messageValidator;

    @Test
    void addpost() {
        Message message = new Message();

        long id = 269;

        message.setId(id);
        message.setTitle("Some watches");
        message.setHeading("Style");
        message.setPrice(79500);
        message.setPlace("Kiev");
        message.setDescription("Very expensive goods");

        messageValidator.validate(message, bindingResult);

        Iterable<Message> messages = messageService.findAll();

        Assert.assertFalse(bindingResult.hasErrors());

        Assert.assertNotNull(messages);
    }
}