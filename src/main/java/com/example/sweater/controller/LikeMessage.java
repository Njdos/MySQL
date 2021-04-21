package com.example.sweater.controller;

import com.example.sweater.domain.LikeMe;
import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.LikeMeService;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class LikeMessage {

    @Autowired
    private LikeMeService likeMeService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/lovedMess")
    public String main(
            @AuthenticationPrincipal User user,
            Model model) {

        List<Message> messages  = messageService.findAll();

        Set<LikeMe> likeMe = likeMeService.findByUserId(user.getId());

        model.addAttribute("messages", messages);
        model.addAttribute("user", user);
        model.addAttribute("loveMess", likeMe);

        return "lovedMess";
    }


}