package com.sumergeTask.sumergeTask.configurations;

import com.sumergeTask.sumergeTask.models.CourseRecommender;
import com.sumergeTask.sumergeTask.models.FirstCourseRecommender;
import com.sumergeTask.sumergeTask.models.SecondCourseRecommender;
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
