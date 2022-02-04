package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.Role;
import com.example.eshop.model.User;
import com.example.eshop.repository.RoleRepository;
import com.example.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailSender;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        if ((user = userRepository.findByEmail(login)) == null) {
            throw new UsernameNotFoundException("User not exist with email : " + login);
        }
        return user;
    }

    public User get(String login) {
        return userRepository.findByEmail(login);
    }

    public User get(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public List<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .toList();
    }

    public User findByActivationCode(String code) throws ObjectNotFoundException {
        return userRepository.findByActivationCode(code)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public void add(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("User with such email already exists");
        }
        user.setActivationCode(UUID.randomUUID().toString());
        mailSender.send(user);
        userRepository.save(user);
    }

    public User activate(User user) {
        Set<Role> role = roleRepository.findById(2L)
                .stream()
                .collect(toSet());

        User foundedUser = userRepository.findByEmail(user.getEmail());

        foundedUser.setActivationCode(null);
        foundedUser.setRoles(role);
        foundedUser.setActivated(true);
        foundedUser.setPassword(user.getPassword());
        return userRepository.save(foundedUser);
    }
}
