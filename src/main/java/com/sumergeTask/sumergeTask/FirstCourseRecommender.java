package com.sumergeTask.sumergeTask;

import org.springframework.stereotype.Component;

public class FirstCourseRecommender implements CourseRecommender{
    @Override
    public String recommendCourse() {
        return "First Course Recommender";
    }
}
