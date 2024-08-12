package com.sumergeTask.sumergeTask.serviceTest;


import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.repositories.CourseRepository;
import com.sumergeTask.sumergeTask.repositories.CourseRepositoryPagination;
import com.sumergeTask.sumergeTask.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseRepositoryPagination courseRepositoryPagination;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCourseById_ShouldReturnCourse_WhenCourseExists() {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        when(courseRepository.findById(mockCourse.getId())).thenReturn(Optional.of(mockCourse));
        Course result = courseService.getCourse(mockCourse.getId());
        Assertions.assertEquals(mockCourse, result);

    }
    @Test
    void getCourseById_ShouldThrowException_WhenCourseDoesNotExist() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> courseService.getCourse(1L));

    }

    @Test
    void getAllCourses_ShouldReturnAllCourses() {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        Course mockCourse2 = new Course();
        mockCourse2.setName("Test Course");
        mockCourse2.setDescription("Test Description");
        mockCourse2.setCredit(1);
        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(mockCourse);
        mockCourses.add(mockCourse2);
        when(courseRepository.findAll()).thenReturn(mockCourses);
        List<Course> result = courseService.getAllCourses();
        Assertions.assertEquals(mockCourses, result);
    }

    @Test
    void shouldAddCourse() {
        Course course = new Course();
        course.setID(1L);
        course.setName("Course Name");
        course.setDescription("Course Description");
        course.setCredit(3);

        courseService.addCourse(course);
        verify(courseRepository).save(course);
    }


    @Test
    void shouldUpdateCourse_WhenCourseExists() {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        Course updatedCourse= new Course();
        updatedCourse.setName("Test Course updatedCourse");
        updatedCourse.setDescription("Test Description updatedCourse");
        updatedCourse.setCredit(8);
        when(courseRepository.findById(mockCourse.getId())).thenReturn(Optional.of(mockCourse));
        courseService.updateCourse(mockCourse.getId(), updatedCourse);
        Assertions.assertEquals(updatedCourse.getName(), mockCourse.getName());
        Assertions.assertEquals(updatedCourse.getDescription(), mockCourse.getDescription());


    }

    @Test
    void shouldNotUpdateCourse_WhenCourseDoesNotExist() {
        Course mockCourse = new Course();
        Course updatedCourse= new Course();
        updatedCourse.setName("Test Course updatedCourse");
        updatedCourse.setDescription("Test Description updatedCourse");
        updatedCourse.setCredit(8);
        when(courseRepository.findById(mockCourse.getId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> courseService.updateCourse(mockCourse.getId(), updatedCourse));
    }

    @Test
    void shouldDeleteCourse_WhenCourseExists() {
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(true);

        courseService.deleteCourse(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }

    @Test
    void shouldThrowException_WhenDeleteCourseNotFound() {
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(false);

         assertThrows(NoSuchElementException.class, () -> {courseService.deleteCourse(courseId);
        });

    }

    @Test
    void shouldGetCoursesByPagination_whenCourseExists() {
        int page = 0;
        int size = 2;
        Course course1=new Course();
        course1.setID(1L);
        course1.setName("Course 1");
        course1.setDescription("Test Description");
        course1.setCredit(1);
        Course course2=new Course();
        course2.setID(2L);
        course2.setName("Course 2");
        course2.setDescription("Test Description");
        course2.setCredit(1);

        List<Course> courses = Arrays.asList(
                course1,course2
        );
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = new PageImpl<>(courses, pageable, courses.size());

        when(courseRepositoryPagination.findAll(pageable)).thenReturn(coursePage);

        Page<Course> result = courseService.getCoursesByPagination(page, size);

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Course 1");
        assertThat(result.getContent().get(1).getName()).isEqualTo("Course 2");
        assertThat(result.getTotalElements()).isEqualTo(2);
    }

    @Test
    void shouldNotGetCoursesByPagination_whenCourseDoeNotExists() {
        assertThrows(IllegalArgumentException.class, () -> courseService.getCoursesByPagination(-1,-1));

    }

    @Test
    void shouldGetCoursesByName_WhenCourseExists() {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(mockCourse);
        when(courseRepository.findByName(mockCourse.getName())).thenReturn(mockCourses);
        List<Course> result = courseService.getCoursesByName(mockCourse.getName());
        Assertions.assertEquals(mockCourses, result);
    }

    @Test
    void shouldNotGetCoursesByName_WhenCourseDoesNotExist() {
        List<Course> mockCourses = new ArrayList<>();
        when(courseRepository.findByName("1L")).thenReturn(mockCourses);
        assertThrows(NoSuchElementException.class, () -> courseService.getCoursesByName("1L"));

    }




}
