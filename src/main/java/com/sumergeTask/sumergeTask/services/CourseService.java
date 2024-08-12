package com.sumergeTask.sumergeTask.services;

import com.sumergeTask.sumergeTask.repositories.CourseRepository;
import com.sumergeTask.sumergeTask.repositories.CourseRepositoryPagination;
import com.sumergeTask.sumergeTask.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseRepositoryPagination courseRepositoryPagination;


    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElseThrow(() -> new NoSuchElementException("Course not found for ID: " + id));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setCredit(updatedCourse.getCredit());
            courseRepository.save(existingCourse);
        }
        else {
            throw new NoSuchElementException("Course not found for ID: " + id);
        }
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Course not found for ID: " + id);

        }

    }

    public Page<Course> getCoursesByPagination(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page index must be non-negative and size must be positive.");
        }
        Pageable pageable = PageRequest.of(page, size);
        return courseRepositoryPagination.findAll(pageable);
    }

    public List<Course> getCoursesByName(String name) {
        List<Course> courses = courseRepository.findByName(name);
        if (courses.isEmpty()) {
            throw new NoSuchElementException("No courses found with name: " + name);
        }
        return courses;
    }

}
