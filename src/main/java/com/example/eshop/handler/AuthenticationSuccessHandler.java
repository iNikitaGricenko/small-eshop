package com.example.eshop.handler;

import com.example.eshop.model.user.CustomUserDetails;
import com.example.eshop.model.user.User;
import com.example.eshop.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserAuthenticationService userAuthenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        Long id = user.getId();
        userAuthenticationService.setLoggedIn(id);
        if (user.getLoginAttempts() < 10) {
            userAuthenticationService.refreshAttempts(id);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
