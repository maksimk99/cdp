package com.epam.cdp.maksim.katuranau.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Objects;

@Configuration
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty("bigdata.datasource.schemaname")
@EnableConfigurationProperties(BigDataProperties.class)
public class StarterConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private static final String MESSAGE = "It's BigData project";
    private static final String SCHEMA = "schema";
    private final ApplicationContext applicationContext;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BigDataProperties bigDataProperties;

    @Autowired
    public StarterConfiguration(final ApplicationContext applicationContext,
                                final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                final BigDataProperties bigDataProperties) {
        this.applicationContext = applicationContext;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.bigDataProperties = bigDataProperties;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        Integer actualAmountOfRow = namedParameterJdbcTemplate.queryForObject(bigDataProperties.getRowsAmountSelect(),
                Collections.singletonMap(SCHEMA, bigDataProperties.getSchemaName()), Integer.class);
        if (Objects.nonNull(actualAmountOfRow) && actualAmountOfRow >= bigDataProperties.getRowsAmount()) {
            System.out.println(MESSAGE);
            int exitCode = SpringApplication.exit(applicationContext);
            System.exit(exitCode);
        }
    }
}
