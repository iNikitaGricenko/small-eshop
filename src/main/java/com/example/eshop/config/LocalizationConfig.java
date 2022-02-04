package com.example.eshop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import static java.util.Locale.ENGLISH;

@Configuration
public class LocalizationConfig {

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(ENGLISH);
        return localeResolver;
    }

    @Bean(name = "messageSource")
	public MessageSource getMessageResource()  {
		ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();

		messageResource.setBasename("classpath:languages/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

}
