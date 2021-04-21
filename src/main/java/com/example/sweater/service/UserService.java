package com.example.sweater.service;

import com.example.sweater.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    boolean searchUserName(User user);

    boolean searchUserNameWithoutUser(String username, Long id);

    boolean searchPhoneWithoutUser(String phone, Long id);

    boolean searchEmailWithoutUser(String email, Long id);

    boolean searchEmail(User user);

    boolean searchPhone(User user);

    void save(User user);

    List<User> findAll();

    Set<User> findById(long id);

    void update(User user);
}


