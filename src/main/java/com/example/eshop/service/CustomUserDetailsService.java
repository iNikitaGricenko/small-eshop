package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Role;
import com.example.eshop.model.User;
import com.example.eshop.repository.RoleRepository;
import com.example.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailSender;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(ObjectNotFoundException::new);
        return user;
    }

    public User get(String login) throws ObjectNotFoundException {
        return userRepository.findByEmail(login)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public User get(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findByActivationCode(String code) throws ObjectNotFoundException {
        return userRepository.findByActivationCode(code)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public void add(User user) {
        userRepository.existsByEmail(user.getEmail())
                .orElseThrow(IllegalStateException::new);

        user.setActivationCode(UUID.randomUUID().toString());
        mailSender.send(user);

        userRepository.save(user);
    }

    public User activate(User user) throws ObjectNotFoundException {
        Set<Role> role = roleRepository.findById(2L)
                .stream()
                .collect(toSet());

        User foundedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(ObjectNotFoundException::new);

        foundedUser.setActivationCode(null);
        foundedUser.setRoles(role);
        foundedUser.setActivated(true);
        foundedUser.setPassword(user.getPassword());

        return userRepository.save(foundedUser);
    }

    public boolean isUserCode(User user, String code) {
        User foundedUser = userRepository.findByActivationCode(code)
                .orElseThrow(IllegalAccessError::new);
        return Objects.equals(user.getEmail(), foundedUser.getEmail());
    }
}
