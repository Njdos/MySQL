package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SomeMessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/usernotice/{id}")
    public String greetidng(@AuthenticationPrincipal User user,
                            @PathVariable("id") long id,
                            Model model) {

        Iterable<Message> messages = messageService.findById(id);
        model.addAttribute("messages", messages);
        model.addAttribute("user", user);

        return "usernotice";
    }
}
