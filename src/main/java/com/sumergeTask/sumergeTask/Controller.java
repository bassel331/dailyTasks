package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CourseService courseService;

    @GetMapping("/view/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
    @GetMapping("/viewAll")
    public List<Course> getAllCourses() {
        return courseService.getCourses();
    }

    @PostMapping("/add")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }
    @PutMapping("/update/{id}")
    public void updateCourse(@RequestBody Course course,@PathVariable int id) {
        Course oldcourse = courseService.getCourseById(id);
        courseService.updateCourse(oldcourse,course);
    }
    @DeleteMapping("delete/{id}")
    public void deleteCourse(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        courseService.deleteCourse(course);
    }
    @GetMapping("/discover")
    public String discover(){
        return courseService.getCourseRecommender().recommendCourse();
    }
}
