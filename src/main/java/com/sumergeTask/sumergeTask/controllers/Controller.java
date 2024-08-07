package com.sumergeTask.sumergeTask.controllers;

import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.models.CourseDTO;
import com.sumergeTask.sumergeTask.CourseMapper;
import com.sumergeTask.sumergeTask.services.CourseService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CourseService courseService;
    private CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);

    @GetMapping("/view/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        return courseMapper.CourseToDTO(course);
    }
    @GetMapping("/viewAll")
    public List<CourseDTO> getAllCourses() {

        return courseMapper.CoursesToDTO(courseService.getAllCourses());
    }

    @PostMapping("/add")
    public void addCourse(@RequestBody Course course) {
        //Course course = courseMapper.DTOToCourse(courseDto);
        courseService.addCourse(course);
    }
    @PutMapping("/update/{id}")
    public void updateCourse(@RequestBody Course course,@PathVariable Long id) {
        courseService.updateCourse(id,course);
    }
    @DeleteMapping("delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    @GetMapping("/viewPag")
    public Page<Course> getCourses(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {

        return courseService.getCoursesByPagination(page, size);
    }
    @GetMapping("/filter/{name}")
    public List<CourseDTO> getCoursesByFilter(@PathVariable String name) {
        return courseMapper.CoursesToDTO(courseService.getCoursesByName(name));
    }
//    @GetMapping("/discover")
//    public String discover(){
//        return courseService.getCourseRecommender().recommendCourse();
//    }
}
