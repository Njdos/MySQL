package com.example.sweater.repos;

import com.example.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndIdNot(String username, Long id);

    User findByPhoneAndIdNot(String phone, Long id);

    User findByEmailAndIdNot(String email, Long id);

    User findByPhone(String photo);

    User findByEmail(String email);

    Set<User> findById(long id);

} 