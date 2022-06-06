package com.bleiny.communities.adapters.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    DataSource dataSource() {
        var dataSource = DataSourceBuilder.create();
        dataSource.url("jdbc:postgresql://localhost:5432/ms-community");
        dataSource.username("postgres");
        dataSource.password("docker");

        return dataSource.build();
    }
}
