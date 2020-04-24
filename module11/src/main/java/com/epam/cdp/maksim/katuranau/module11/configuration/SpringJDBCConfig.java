package com.epam.cdp.maksim.katuranau.module11.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc-connection.properties")
public class SpringJDBCConfig {
    @Value("${db.driver.class.name}")
    private String DB_DRIVER_CLASS_NAME;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.user}")
    private String DB_USER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    /**
     * Configuration data source (MySQL database).
     *
     * @return DataSource utility class to access a datasource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    /**
     * Gets named parameter jdbc template.
     *
     * @param dataSource is settings connection to database
     * @return JdbcTemplate template class with a basic set of JDBC operations
     */
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(
            final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Gets driver manager data source.
     *
     * @param dataSource the data source
     * @return the driver manager data source
     */
    @Bean
    public DataSourceTransactionManager getDriverManagerDataSource(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
