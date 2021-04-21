package com.example.sweater.service;

import com.example.sweater.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MessageService {

    void save(Message message);

    void update(Message message);

    void delete(Message message);

    void deleteById(Long id);

    Message getById(Long id);

    List<Message> filterTitleAndHeadingAndPrice1AndPrice2(String title, String heading, long price1, long price2);

    List<Message> filterTitleAndPrice1AndPrice2 (String title, long price1, long price2);

    List<Message> filterHeadingAndPrice1AndPrice2 (String heading,long price1, long price2);

    List<Message> filterPrice1AndPrice2 (long price1, long price2);

    Set<Message> findById(long id);

    List<Message> findAll();

}
