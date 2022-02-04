package com.example.eshop;

import com.example.eshop.config.LocalizationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
public class EShopApplication implements WebMvcConfigurer {

    private final LocalizationConfig localizationConfig;

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localizationConfig.localeChangeInterceptor());
    }
}
