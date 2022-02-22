package com.example.eshop.component;

import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import com.example.eshop.service.LoginAttemptsService;
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
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    private final CustomUserDetailsService userService;
    private final LoginAttemptsService loginAttemptsService;

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
                loginAttemptsService.lock(email);
                exception = new LockedException("This account was locked because of brute try of log in");
            }
            loginAttemptsService.decreesAttempt(email);
        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
