package com.example.sweater.service;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepo messageRepo;

    @Override
    public void save(Message message) {
        messageRepo.save(message);
    }

    @Override
    public void update(Message message) {
        messageRepo.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepo.delete(message);
    }

    @Override
    public void deleteById(Long id) {
        if (messageRepo.existsById(id)) {
            messageRepo.deleteById(id);
        }
    }

    @Override
    public Message getById(Long id) {
        return messageRepo.findById(id).get();
    }

    @Override
    public List<Message> filterTitleAndHeadingAndPrice1AndPrice2(String title, String heading, long price1, long price2) {
        return messageRepo.findByTitleContainingAndHeadingContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (title, heading, price1, price2);
    }

    @Override
    public List<Message> filterTitleAndPrice1AndPrice2(String title, long price1, long price2) {
        return messageRepo.findByTitleContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (title, price1, price2);
    }

    @Override
    public List<Message> filterHeadingAndPrice1AndPrice2(String heading, long price1, long price2) {
        return messageRepo.findByHeadingContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (heading, price1, price2);
    }

    @Override
    public List<Message> filterPrice1AndPrice2(long price1, long price2) {
        return messageRepo.findByPriceGreaterThanEqualAndPriceLessThanEqual (price1, price2);
    }

    @Override
    public Set<Message> findById(long id) {
        return messageRepo.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }
}
