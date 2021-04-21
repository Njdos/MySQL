package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.LikeMeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainControllerTest {

    @Autowired
    private LikeMeService likeMeService;

    @Test
    void main() {
        User user = new User();
        long idUser = 269;
        user.setId(idUser);

        List<Long> likeMeList = likeMeService.getListLikeMeIdByUserId(user.getId());

        Assert.assertNotNull(likeMeList);
    }

    @Test
    void addLIke() {
        User user = new User();
        long idUser = 269;

        user.setId(idUser);

        long id = 269;

        Assert.assertTrue(likeMeService.findByMessIdAndUserId( id, user.getId() ));

    }

    @Test
    void deleteLIke() {
    }
}