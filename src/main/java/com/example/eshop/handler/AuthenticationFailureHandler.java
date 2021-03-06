package com.example.eshop.handler;

import com.example.eshop.model.user.User;
import com.example.eshop.service.CustomUserDetailsService;
import com.example.eshop.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CustomUserDetailsService userService;
    private final UserAuthenticationService userAuthenticationService;

    @SneakyThrows
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String email = request.getParameter("username");
        User user = userService.get(email);

        if (user.isActivated() && user.isNonLocked()) {
            if (user.getLoginAttempts() <= 0) {
                userAuthenticationService.lock(email);
                exception = new LockedException("This account was locked because of brute try of log in");
            }
            userAuthenticationService.decreesAttempt(user.getId());
        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
