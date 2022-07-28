package ru.scamburger.Soundger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.scamburger.Soundger.service.FileStorage.FileStorageProvider;
import ru.scamburger.Soundger.service.FileStorage.PhysicalFileStorageProvider;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public static FileStorageProvider getFileStorageProvider() {
        return new PhysicalFileStorageProvider();
    }

}
