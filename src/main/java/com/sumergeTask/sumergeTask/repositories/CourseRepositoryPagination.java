package com.sumergeTask.sumergeTask.repositories;

import com.sumergeTask.sumergeTask.models.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepositoryPagination extends PagingAndSortingRepository<Course, Long> {

}
