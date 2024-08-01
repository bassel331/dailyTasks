package com.sumergeTask.sumergeTask;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("firstCourseRecommender")
public class FirstCourseRecommender implements CourseRecommender{
    @Override
    public String recommendCourse() {
        return "First Course Recommender";
    }
}
