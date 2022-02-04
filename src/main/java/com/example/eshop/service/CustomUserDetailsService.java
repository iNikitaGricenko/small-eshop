package com.example.eshop.service;

import com.example.eshop.exception.EmailExistsException;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.CustomUserDetail;
import com.example.eshop.model.Role;
import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailService mailSender;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(ObjectNotFoundException::new);
        CustomUserDetail userDetail = new CustomUserDetail();
        userDetail.setUser(user);
        return userDetail;
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

    public void add(User user) throws EmailExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistsException("User with that email already exists");
        }

        user.setActivationCode(UUID.randomUUID().toString());
        mailSender.send(user);

        userRepository.save(user);
    }

    public User activate(User user) throws ObjectNotFoundException {
        User foundedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(ObjectNotFoundException::new);
        Role role = Role.USER;

        foundedUser.setActivationCode(null);
        foundedUser.setRole(role);
        foundedUser.setActivated(true);
        foundedUser.setPassword(user.getPassword());

        return userRepository.save(foundedUser);
    }

    public boolean isUserCode(User user, String code) throws ObjectNotFoundException {
        User foundedUser = userRepository.findByActivationCode(code)
                .orElseThrow(ObjectNotFoundException::new);
        return Objects.equals(user.getEmail(), foundedUser.getEmail());
    }
}
