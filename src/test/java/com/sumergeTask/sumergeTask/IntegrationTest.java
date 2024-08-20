package com.sumergeTask.sumergeTask;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumergeTask.sumergeTask.controllers.Controller;
import com.sumergeTask.sumergeTask.mappers.CourseMapper;
import com.sumergeTask.sumergeTask.models.Course;
import com.sumergeTask.sumergeTask.models.CourseDTO;
import com.sumergeTask.sumergeTask.services.CourseService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class IntegrationTest {

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);

    }

    @BeforeAll
    static void beforeAll()  {
        mySQLContainer.start();
//        System.out.println(mySQLContainer.getJdbcUrl());
//        System.out.println(mySQLContainer.getUsername());
//        System.out.println(mySQLContainer.getPassword());

    }
    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    void shouldAddCourse() throws Exception {
        Course mockCourse = new Course();
        mockCourse.setName("Test Course");
        mockCourse.setID(33L);
        mockCourse.setDescription("Test Description");
        String courseJson = objectMapper.writeValueAsString(mockCourse);
System.out.println(courseJson);
        mockMvc.perform(post("/add")
                        .header("x-validation-report", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnAllCourses() throws Exception {

        MvcResult result = mockMvc.perform(get("/viewAll")
                        .header("x-validation-report", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<CourseDTO> responseCourses = objectMapper.readValue(responseBody, new TypeReference<List<CourseDTO>>() {
        });
        assertEquals(0, responseCourses.size(), "The response list should contain 1 courses");

    }

}
