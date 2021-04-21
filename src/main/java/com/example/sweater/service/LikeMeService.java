package com.example.sweater.service;

import com.example.sweater.domain.LikeMe;

import java.util.List;
import java.util.Set;

public interface LikeMeService {

    void save(Long idMess, Long idUser);

    void delete(Long idMess, Long idUser);

    LikeMe findById(Long id);

    Set<LikeMe> findByUserId(Long id);

    List<Long> getListLikeMeIdByUserId(Long id);

    boolean findByMessIdAndUserId(Long messId, Long userId);
}
