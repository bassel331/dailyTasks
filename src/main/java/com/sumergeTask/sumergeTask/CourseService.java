package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class CourseService {
    private CourseRecommender courseRecommender;

    @Autowired
    public CourseService(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
    @Autowired
    public void setCourseRecommender(@Qualifier("secondCourseRecommender")CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
    public String getRecommendation() {
        return courseRecommender.recommendCourse();
    }

}
