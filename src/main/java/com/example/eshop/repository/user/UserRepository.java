package com.example.eshop.repository.user;

import com.example.eshop.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    boolean existsByLogin(String login);
}
