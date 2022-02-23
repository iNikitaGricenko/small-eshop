package com.example.eshop.service;

import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAttemptsService {

    private final UserRepository userRepository;
    private final MailService mailSender;

    public void setLoggedIn(Long id) {
        userRepository.setLoggedIn(id);
    }

    public void lock(Long id) throws ObjectNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);

        user.setNonLocked(false);
        userRepository.save(user);

        mailSender.sendUnlockUrl(user);
    }

    public void lock(String email) throws ObjectNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(ObjectNotFoundException::new);

        user.setNonLocked(false);
        userRepository.save(user);

        mailSender.sendUnlockUrl(user);
    }

    public void unlock(Long id) {
        userRepository.unlock(id);
    }

    public void unlock(String email) {
        userRepository.unlock(email);
    }

    public void refreshAttempts(Long id) {
        userRepository.refreshAttempts(id);
    }

    public int getAttemptsCount(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new)
                .getLoginAttempts();
    }

    public void decreesAttempt(String email) throws ObjectNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(ObjectNotFoundException::new);
        short loginAttempts = (short) (user.getLoginAttempts() - 1);
        user.setLoginAttempts(loginAttempts);
        userRepository.save(user);
    }

}
