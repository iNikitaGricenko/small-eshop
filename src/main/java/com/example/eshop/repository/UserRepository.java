package com.example.eshop.repository;

import com.example.eshop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByActivationCode(String code);

    @Modifying
    @Query(value = "UPDATE User e " +
            "SET last_log_in = now() " +
            "WHERE e.id = ?1")
    void setLoggedIn(Long id);

    @Modifying
    @Query(value = "UPDATE User e " +
            "SET non_locked = true " +
            "WHERE e.id = ?1")
    void unlock(Long id);

    @Modifying
    @Query(value = "UPDATE User e " +
            "SET non_locked = true " +
            "WHERE e.email = ?1")
    void unlock(String login);

    @Modifying
    @Query(value = "UPDATE User e " +
            "SET login_attempts = 10 " +
            "WHERE e.id = ?1")
    void refreshAttempts(Long id);
}
