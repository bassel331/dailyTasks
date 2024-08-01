package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
public class AppConfig {

    @Bean(name="firstCourseRecommender")
    @Primary
    public CourseRecommender firstCourseRecommender() {
        return new FirstCourseRecommender();
    }
    @Bean(name="secondCourseRecommender")

    public CourseRecommender secondCourseRecommender() {
        return new SecondCourseRecommender();
    }

    @Bean
    public CourseService courseService() {
        return new CourseService(secondCourseRecommender());
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

        // Load and execute schema.sql
        try {
            String schemaSql = new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")));
            //System.out.println("Executing schema SQL: " + schemaSql);
            //jdbcTemplate.execute(schemaSql);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jdbcTemplate;
    }
}
