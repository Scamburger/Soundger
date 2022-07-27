package ru.scamburger.Soundger.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public static StrongPasswordEncryptor getEncryptor() {
        return new StrongPasswordEncryptor();
    }
}
