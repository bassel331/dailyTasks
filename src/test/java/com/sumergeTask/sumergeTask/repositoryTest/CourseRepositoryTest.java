package com.sumergeTask.sumergeTask.repositoryTest;

import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.repositories.CourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void tryingToAddNewCourse() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
       Course c= courseRepository.save(course);
        Assertions.assertThat(c).isNotNull();

    }

    @Test
    public void tryingToGetAllCourses() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
        Course course2 = new Course();
        course2.setName("test");
        course2.setDescription("d");
        course2.setCredit(2);
        courseRepository.save(course);
        courseRepository.save(course2);
        List<Course> courses = courseRepository.findAll();
        Assertions.assertThat(courses.size()).isEqualTo(2);
        Assertions.assertThat(courses).isNotNull();

    }

    @Test
    public void tryingToGetCourseById() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
        courseRepository.save(course);
        Course course2 = courseRepository.findById(course.getId()).orElse(null);
        Assertions.assertThat(course2).isNotNull();

    }

    @Test
    public void tryingToGetCourseByName() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
        courseRepository.save(course);
        List<Course> courses =courseRepository.findByName(course.getName());
        System.out.println(courses);
        Assertions.assertThat(courses).isNotNull();

    }

    @Test
    public void tryingToDeleteCourse() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
        courseRepository.save(course);
        courseRepository.delete(course);
        Optional<Course> course1= courseRepository.findById(course.getId());
        Assertions.assertThat(course1).isEmpty();

    }

    @Test
    public void tryingToUpdateCourse() {
        Course course = new Course();
        course.setName("test");
        course.setDescription("d");
        course.setCredit(2);
        courseRepository.save(course);
        Course course2 = courseRepository.findById(course.getId()).orElse(null);
        course2.setName("test2");
        courseRepository.save(course2);
        Course course3 = courseRepository.findById(course.getId()).orElse(null);
        Assertions.assertThat(course3.getName()).isEqualTo(course.getName());
        //System.out.println((course3.getName()));
    }
}
