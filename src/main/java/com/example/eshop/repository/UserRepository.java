package com.example.eshop.repository;

import com.example.eshop.model.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByActivationCode(String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User e SET last_log_in = now() WHERE e.id = ?1")
    void setLoggedIn(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User e SET non_locked = false WHERE e.id = ?1")
    boolean lock(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User e SET non_locked = true WHERE e.id = ?1")
    boolean unlock(Long id);

}
