package com.sumergeTask.sumergeTask.ControllerTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumergeTask.sumergeTask.mappers.CourseMapper;
import com.sumergeTask.sumergeTask.controllers.Controller;
import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.models.CourseDTO;
import com.sumergeTask.sumergeTask.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnCourseById() throws Exception {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        CourseDTO mockCourseDTO = new CourseDTO();
        mockCourseDTO.setName("Test Course");

        when(courseService.getCourse(anyLong())).thenReturn(mockCourse);
        when(courseMapper.CourseToDTO(mockCourse)).thenReturn(mockCourseDTO);

        mockMvc.perform(get("/view/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(mockCourseDTO)));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        Course mockCourse = new Course();
        Course mockCourse2 = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setDescription("Test Description");
        mockCourse.setCredit(1);
        CourseDTO mockCourseDTO = new CourseDTO();
        mockCourseDTO.setName("Test Course");
        List<CourseDTO> mockCourseDTOList = new ArrayList<>();
        List<Course> mockCourseList = new ArrayList<>();
        mockCourseList.add(mockCourse);
        mockCourseList.add(mockCourse2);

        when(courseService.getAllCourses()).thenReturn(mockCourseList);
        when((courseMapper.CoursesToDTO(mockCourseList))).thenReturn(mockCourseDTOList);

        MvcResult result = mockMvc.perform(get("/viewAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<CourseDTO> responseCourses = objectMapper.readValue(responseBody, new TypeReference<List<CourseDTO>>() {
        });
        assertEquals(2, responseCourses.size(), "The response list should contain 2 courses");

    }

    @Test
    void shouldAddCourse() throws Exception {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        String courseJson = objectMapper.writeValueAsString(mockCourse);

        mockMvc.perform(post("/add")
                        .contentType("application/json")
                        .content(courseJson))
                .andExpect(status().isOk());

    }

    @Test
    void shouldUpdateCourse() throws Exception {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setID(22L);
        String courseJson = objectMapper.writeValueAsString(mockCourse);

        mockMvc.perform(put("/update/{id}", mockCourse.getId())
                        .contentType("application/json")
                        .content(courseJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCourse() throws Exception {

        mockMvc.perform(delete("/delete/{id}", 7))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnPaginatedCourses() throws Exception {
    Course course1 = new Course();
    Course course2 = new Course();
    List<Course> courseList = Arrays.asList(course1, course2);
    Page<Course> coursePage = new PageImpl<>(courseList, PageRequest.of(0, 10), courseList.size());

    when(courseService.getCoursesByPagination(anyInt(),anyInt())).
                        thenReturn(coursePage);

        mockMvc.perform(get("/viewPag")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
}

    @Test
    void shouldReturnCoursesByFilter() throws Exception {
        Course course1 = new Course();
        course1.setID(1L);
        course1.setName("Math");
        course1.setDescription("Math");
        course1.setCredit(1);
        Course course2 = new Course();
        course2.setID(2L);
        course2.setName("English");
        course2.setDescription("English");
        course2.setCredit(1);
        List<Course> courseList = Arrays.asList(course1, course2);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Math");
        courseDTO.setId(1L);
        List<CourseDTO> courseDTOList = Arrays.asList(
                courseDTO
        );

        when(courseService.getCoursesByName(anyString())).thenReturn(courseList);
        when(courseMapper.CoursesToDTO(courseList)).thenReturn(courseDTOList);

        mockMvc.perform(get("/filter/Math"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Math"));
    }




}
