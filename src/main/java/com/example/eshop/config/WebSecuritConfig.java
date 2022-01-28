package com.example.eshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecuritConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userService;

    public WebSecuritConfig(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                        .antMatchers("/css/*", "/login").permitAll()
                        .antMatchers("/").authenticated()
                        .antMatchers("/my-order").authenticated()
                        .antMatchers("/*").permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .failureForwardUrl("/login?error")
                        .defaultSuccessUrl("/")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                .and()
                    .csrf()
                        .disable();

    }
}
