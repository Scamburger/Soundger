package ru.scamburger.Soundger.configuration;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public static PasswordEncryptor getEncryptor() {
        return new StrongPasswordEncryptor();
    }
}
