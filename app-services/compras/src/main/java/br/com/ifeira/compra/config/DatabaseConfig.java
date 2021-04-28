package br.com.ifeira.compra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    public final String DRIVER_CLASS_NAME;
    public final String URL;
    public final String USERNAME;
    public final String PASSWORD;

    public DatabaseConfig(@Value("database.driver_class_name") String DRIVER_CLASS_NAME,
                          @Value("database.url") String URL,
                          @Value("database.username") String USERNAME,
                          @Value("database.password") String PASSWORD) {
        this.DRIVER_CLASS_NAME = DRIVER_CLASS_NAME;
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.DRIVER_CLASS_NAME);
        dataSource.setUrl(this.URL);
        dataSource.setUsername(this.USERNAME);
        dataSource.setPassword(this.PASSWORD);
        return dataSource;
    }

}
