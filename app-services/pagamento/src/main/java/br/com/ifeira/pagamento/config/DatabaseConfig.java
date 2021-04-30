package br.com.ifeira.pagamento.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    public final String DRIVER_CLASS_NAME;
    public final String URL;
    public final String USERNAME;
    public final String PASSWORD;

    public DatabaseConfig(@Value("${bd.driverClassName}") String DRIVER_CLASS_NAME,
                          @Value("${bd.url}") String URL,
                          @Value("${bd.username}") String USERNAME,
                          @Value("${bd.password}") String PASSWORD) {
        this.DRIVER_CLASS_NAME = DRIVER_CLASS_NAME;
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().url(this.URL)
                .driverClassName(this.DRIVER_CLASS_NAME)
                .username(this.USERNAME)
                .password(this.PASSWORD)
                .build();

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
