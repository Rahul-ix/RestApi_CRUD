package com.restapi_example1.repository;

import com.restapi_example1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmail(String email);
}