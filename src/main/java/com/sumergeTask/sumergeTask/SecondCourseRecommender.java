package com.sumergeTask.sumergeTask;

import org.springframework.stereotype.Component;


public class SecondCourseRecommender implements CourseRecommender{
    @Override
    public String recommendCourse() {
        return "Second Course Recommender";
    }
}
