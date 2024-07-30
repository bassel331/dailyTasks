package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CourseService {
    private CourseRecommender courseRecommender;

    @Autowired
    public CourseService(@Qualifier("firstCourseRecommender")CourseRecommender courseRecommender) {
        courseRecommender = courseRecommender;
    }
    @Autowired
    public void setCourseRecommender(@Qualifier("secondCourseRecommender")CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
}
