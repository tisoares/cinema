package br.com.as.cinema.internal.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfiguration
@EnableConfigurationProperties(CinemaProperties.class)
public class CinemaConfig {

}
