package com.epam.cdp.maksim.katuranau.module9.task1.config;

import com.epam.cdp.maksim.katuranau.module9.task1.util.property.PropertyReader;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:sql-query.properties")
@ComponentScan("com.epam.cdp.maksim.katuranau.module9.task1")
public class AppConfiguration {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean(value = "customProperties")
    public Properties property() {
        return PropertyReader.getProperties(System.getProperty("path"));
    }
}
