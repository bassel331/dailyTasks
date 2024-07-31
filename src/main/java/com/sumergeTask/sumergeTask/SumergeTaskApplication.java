package com.sumergeTask.sumergeTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SumergeTaskApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(SumergeTaskApplication.class, args);

		Course course1 =context.getBean(Course.class);
		course1.setId(1);
		course1.setName("Sumerge Task");
		course1.setDescription("Sumerge Task description");
		course1.setCredit(5);

		Course course2 =context.getBean(Course.class);
		course2.setId(2);
		course2.setName("Sumerge Taskkkkk");
		course2.setDescription("Sumerge Task descriptionnnn");
		course2.setCredit(5);

		CourseService courseService = context.getBean(CourseService.class);
		courseService.addCourse(course1);
		courseService.addCourse(course2);
        List<Course> coursesAvailable =courseService.getCourses();
		System.out.println("Courses Available now are :"+coursesAvailable);
		System.out.println("-----------------");

		courseService.deleteCourse(course1);
		coursesAvailable=courseService.getCourses();
		System.out.println("After deletion become:"+coursesAvailable);
		System.out.println("-----------------");

		courseService.updateCourse(course2,"cs4","desc" ,8);
		coursesAvailable = courseService.getCourses();
		System.out.println("After updating:"+coursesAvailable);
		System.out.println("-----------------");

	}

}
