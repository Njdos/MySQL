package com.example.sweater.controller;

import com.example.sweater.domain.LikeMe;
import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.LikeMeService;
import com.example.sweater.service.MessageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class LikeMessageTest {

    @Autowired
    private LikeMeService likeMeService;

    @Autowired
    private MessageService messageService;

    @Test
    void main() {
        User user = new User();

        long id  = 172;
        user.setId(id);


        List<Message> messages  = messageService.findAll();

        Set<LikeMe> likeMe = likeMeService.findByUserId(user.getId());

        Assert.assertNotNull(messages);

        Assert.assertNotNull(likeMe);
    }
}
