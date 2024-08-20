package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.example.demo.AppConfigg;

@Configuration
@ComponentScan(basePackages = "com.sumergeTask.sumergeTask")
@Import(com.example.demo.AppConfigg.class)
public class AppConfig {

    @Bean
    @Qualifier("secondCourseRecommender")
    public CourseRecommender secondCourseRecommender() {

        return new SecondCourseRecommender();
    }


    @Bean
    @Scope("prototype")
    public Course course() {
        return new Course();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Courses");
        dataSource.setUsername("postgres");
        dataSource.setPassword("bassel");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            String schemaSql = new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jdbcTemplate;
    }
}
