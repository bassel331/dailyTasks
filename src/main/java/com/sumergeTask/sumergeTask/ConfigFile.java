package com.sumergeTask.sumergeTask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigFile {
    @Bean(name="firstCourseRecommender")
    @Primary
    public CourseRecommender firstCourseRecommender() {
        return new FirstCourseRecommender();
    }
    @Bean(name="secondCourseRecommender")
    public CourseRecommender secondCourseRecommender() {
        return new SecondCourseRecommender();
    }


}
