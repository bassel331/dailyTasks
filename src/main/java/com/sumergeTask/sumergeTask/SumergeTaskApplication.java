package com.sumergeTask.sumergeTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class SumergeTaskApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CourseService courseService = context.getBean(CourseService.class);
		CourseRecommender courseRecommender = context.getBean(CourseRecommender.class);
		System.out.println(courseService.getRecommendation());
		System.out.println(courseRecommender.recommendCourse());

	}

}
