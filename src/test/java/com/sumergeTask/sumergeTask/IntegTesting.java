package com.sumergeTask.sumergeTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumergeTask.sumergeTask.models.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.sumergeTask.sumergeTask.repositories.CourseRepository;
import com.sumergeTask.sumergeTask.services.CourseService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegTesting {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    private String createURLWithPort() {
        return "http://localhost:" + port + "/viewAll";
    }

    @Test
    @Sql(statements = "INSERT INTO courses(id, name, description, credit) VALUES (2, 'john', 'oo', 1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM courses WHERE id='2'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCoursesList() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Course>> response = restTemplate.exchange(
                createURLWithPort(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Course>>(){});
        List<Course> orderList = response.getBody();
        assert orderList != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(orderList.size(), courseService.getAllCourses().size());
        assertEquals(orderList.size(), courseRepository.findAll().size());
    }
}
