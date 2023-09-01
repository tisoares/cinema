package br.com.as.cinema.internal.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfiguration
@EnableConfigurationProperties(CinemaProperties.class)
@ConfigurationProperties(prefix = "spring.liquibase", ignoreUnknownFields = false)
public class CinemaConfig {

}
