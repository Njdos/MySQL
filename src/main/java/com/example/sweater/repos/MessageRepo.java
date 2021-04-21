package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTitleContainingAndHeadingContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (String title, String heading, long price1, long price2);

    List<Message> findByTitleContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (String title, long price1, long price2);

    List<Message> findByHeadingContainingAndPriceGreaterThanEqualAndPriceLessThanEqual (String heading,long price1, long price2);

    List<Message> findByPriceGreaterThanEqualAndPriceLessThanEqual (long price1, long price2);

    List<Message> findAll();

    Set<Message> findById(long id);
}