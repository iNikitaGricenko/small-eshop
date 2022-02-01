package com.example.eshop.service.user;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.user.Role;
import com.example.eshop.model.user.User;
import com.example.eshop.repository.user.RoleRepository;
import com.example.eshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
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
        if (userRepository.existsByLogin(user.getLogin())) {
            return;
        }
        Set<Role> role = roleRepository.findById(2L).stream().collect(toSet());
        user.setRoles(role);
        userRepository.save(user);
    }

    public User get(String login) {
        return userRepository.findByLogin(login);
    }

    public User get(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

}
