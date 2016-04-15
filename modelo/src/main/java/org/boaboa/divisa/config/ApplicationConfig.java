package org.boaboa.divisa.config;

import org.boaboa.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Patricio A. Pérez Valverde
 */
@Configuration
@PropertySource("classpath:divisa-modelo.properties")
@ComponentScan(basePackageClasses = {App.class})
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
