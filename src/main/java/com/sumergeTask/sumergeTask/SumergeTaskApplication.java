package com.sumergeTask.sumergeTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;


public class SumergeTaskApplication {

	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CourseService courseService = context.getBean(CourseService.class);
		System.out.println(courseService.getRecommendation());

//		Course course1 =context.getBean(Course.class);
//		course1.setId(1);
//		course1.setName("Sumerge Task");
//		course1.setDescription("Sumerge Task description");
//		course1.setCredit(5);
//		System.out.println(course1);
//
//		Course course2 =context.getBean(Course.class);
//		course2.setId(3);
//		course2.setName("333");
//		course2.setDescription(" description 3");
//		course2.setCredit(5);
//		System.out.println(course2);
//
//		//courseService.addCourse(course1);
//		//courseService.addCourse(course2);
//		List<Course> coursesAvailable =courseService.getCourses();
//		System.out.println("Courses Available now are :"+coursesAvailable);
//		System.out.println("-----------------");
//
//		courseService.deleteCourse(course2);
//		coursesAvailable=courseService.getCourses();
//		System.out.println("After deletion become:"+coursesAvailable);
//		System.out.println("-----------------");

	}

}
