package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import com.example.sweater.utils.validator.UserValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Mock
    private BindingResult bindingResult;

    @Test
    void addUser() {

        User user = new User();

        user.setFirstname("Who");
        user.setLastname("Are");
        user.setUsername("You");
        user.setDate("2021-04-09");
        user.setPassword("123321");
        user.setPassword2("123321");
        user.setEmail("dfcz652256@gmail.com");
        user.setPhone("50-53-79-737");

        userValidator.validate(user, bindingResult);

        Assert.assertFalse(bindingResult.hasErrors());

        Assert.assertTrue(userService.searchEmail(user));

        Assert.assertTrue(userService.searchPhone(user));

        Assert.assertTrue(!user.getDate().equals(""));

        Assert.assertTrue(user.getPassword().equals(user.getPassword2()));
    }
}