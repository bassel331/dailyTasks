package com.sumergeTask.sumergeTask.repositories;

import com.sumergeTask.sumergeTask.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
   List<Course> findByName(String name);
}
