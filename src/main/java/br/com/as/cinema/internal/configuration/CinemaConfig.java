package br.com.as.cinema.internal.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@AutoConfiguration
@EnableJpaRepositories
@EnableJpaAuditing
@EnableConfigurationProperties(CinemaProperties.class)
@ConfigurationProperties(prefix = "spring.liquibase", ignoreUnknownFields = false)
public class CinemaConfig {
    @Bean
    public SpringSecurityAuditorAware auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
