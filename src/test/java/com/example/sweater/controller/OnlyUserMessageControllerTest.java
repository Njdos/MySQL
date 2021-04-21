package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.MessageService;
import com.example.sweater.utils.validator.MessageValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest
class OnlyUserMessageControllerTest {

    @Mock
    private MessageService messageService;

    @Mock
    private MessageValidator messageValidator;

    @Mock
    private BindingResult bindingResult;

    @Test
    void updateMessage() {
        User user = new User();

        user.setFirstname("Who");
        user.setLastname("Are");
        user.setUsername("You");
        user.setDate("2021-04-09");
        user.setPassword("123321");
        user.setPassword2("123321");
        user.setEmail("dfcz652256@gmail.com");
        user.setPhone("50-53-79-737");

        long id = 270;

        Message message  = new Message();

        message.setId(id);
        message.setTitle("Some watches");
        message.setHeading("Style");
        message.setPrice(79500);
        message.setPlace("Kiev");
        message.setDescription("Very expensive goods");
        message.setAuthor(user);

        messageValidator.validate(message, bindingResult);
        messageService.update(message);
        Iterable<Message> messages = messageService.findAll();

        Assert.assertFalse(bindingResult.hasErrors());

        Assert.assertNotNull(messages);
    }

    @Test
    void userMessges() {
    }

    @Test
    void deleteMessage() {

        long messageId = 271;

        messageService.deleteById(messageId);

    }
}