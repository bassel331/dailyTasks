package com.sumergeTask.sumergeTask;


import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.models.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    Course DTOToCourse(CourseDTO dto);
    CourseDTO CourseToDTO(Course course);
    List<CourseDTO> CoursesToDTO(List<Course> courses);
}


