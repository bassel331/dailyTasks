package com.sumergeTask.sumergeTask;

import org.springframework.stereotype.Component;

@Component("secondCourseRecommender")
public class SecondCourseRecommender implements CourseRecommender{
    @Override
    public String recommendCourse() {
        return "Second Course Recommender";
    }
}
