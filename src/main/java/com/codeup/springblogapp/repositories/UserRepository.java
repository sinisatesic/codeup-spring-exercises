package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User findByUsername(String username);
}
