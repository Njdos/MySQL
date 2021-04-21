package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class SendSmsController {

    @Autowired
    private UserService userService;

    @GetMapping("/sendSms/{messAuthId}")
    public String sendSms(
            @AuthenticationPrincipal User user,
            @PathVariable User messAuthId,
            Model model
    ){
        Set<Message> messages = messAuthId.getMessages();

        Iterable<User> users = userService.findById(messAuthId.getId());

        model.addAttribute("countMess", messages.size());
        model.addAttribute("userMess", users);
        model.addAttribute("user", user);

        return "sendSms";
    }




}

