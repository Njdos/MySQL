package com.example.sweater.repos;

import com.example.sweater.domain.LikeMe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LikeMeRepo extends JpaRepository<LikeMe, Long> {


    Set<LikeMe> findByUserId(Long id);

    LikeMe findByMessIdAndUserId(Long messId, Long userId);

}