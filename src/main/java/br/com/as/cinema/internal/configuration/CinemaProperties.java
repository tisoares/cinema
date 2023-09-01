package br.com.as.cinema.internal.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = CinemaConstants.APPLICATION_PROPERTY_PREFIX)
public class CinemaProperties {

}
