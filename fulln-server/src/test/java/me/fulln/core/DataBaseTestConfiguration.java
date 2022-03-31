package me.fulln.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;


public class DataBaseTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public MySQLContainer<?> mySQLContainer() {
        return new MySQLContainer<>("mysql:8.0")
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    @FlywayDataSource
    public DataSource datasource(MySQLContainer<?> mySQLContainer) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mySQLContainer.getJdbcUrl());
        hikariConfig.setUsername(mySQLContainer.getUsername());
        hikariConfig.setPassword(mySQLContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
