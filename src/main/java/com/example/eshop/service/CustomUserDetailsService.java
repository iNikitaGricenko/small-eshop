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

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByEmail(login);
        CustomUserDetails userDetails;
        if (user!=null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        } else {
            throw new UsernameNotFoundException("User not exist with email : " + login);
        }
        return userDetails;
    }

    public void add(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            return;
        }
        user.setActivationCode(UUID.randomUUID().toString());

        String subject = "Activation code";
        String message = String.format(
                "Hello, %s! \n" +
                "You send request for registration. \n" +
                "Please, visit actiovation link: http://localhost/user/activate/%s",
                user.getFirstName(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(), subject, message);
        repository.save(user);
    }

    public User activate(String code) throws ObjectNotFoundException {
        User user = repository.findByActivationCode(code)
                .orElseThrow(ObjectNotFoundException::new);
        Set<Role> role = roleRepository.findById(2L)
                .stream()
                .collect(toSet());

        user.setActivationCode(null);

        user.setRoles(role);
        user.setActivated(true);
        return repository.save(user);
    }

    public User get(String login) {
        return repository.findByEmail(login);
    }

    public User get(Long id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

    public List<User> getAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public User findByActivationCode(String code) throws ObjectNotFoundException {
        return repository.findByActivationCode(code).orElseThrow(ObjectNotFoundException::new);
    }
}
