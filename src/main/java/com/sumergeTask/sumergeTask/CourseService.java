package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseRepositoryPagination courseRepositoryPagination;


    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
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
    }
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    public Page<Course> getCoursesByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepositoryPagination.findAll(pageable);
    }
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByName(name);
    }


}



























//package com.sumergeTask.sumergeTask;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Component
//public class CourseService {
//    private CourseRecommender courseRecommender;
//    private JdbcTemplate template;
//
//    @Autowired
//    public CourseService(@Qualifier("firstCourseRecommender")CourseRecommender courseRecommender) {
//        this.courseRecommender = courseRecommender;
//    }
////    @Autowired
////    public void setCourseRecommender(@Qualifier("secondCourseRecommender")CourseRecommender courseRecommender) {
////        this.courseRecommender = courseRecommender;
////    }
//public CourseRecommender getCourseRecommender() {
//    return courseRecommender;
//}
//    public JdbcTemplate getTemplate() {
//        return template;
//    }
//    @Autowired
//    public void setTemplate(JdbcTemplate template) {
//        this.template = template;
//    }
//    public void addCourse(Course course) {
//        String sql="insert into Course values(?,?,?,?)";
//        int rows =template.update(sql,course.getId(),course.getName(),course.getDescription(),course.getCredit());
//
//        System.out.println(rows);
//    }
//    public List<Course> getCourses() {
//        String sql="select * from Course";
//        return template.query(sql, new RowMapper<Course>() {
//            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Course course = new Course();
//                course.setId(rs.getInt("Id"));
//                course.setName(rs.getString("Name"));
//                course.setDescription(rs.getString("Description"));
//                course.setCredit(rs.getInt("Credit"));
//                return course;
//
//            }
//        });
//    }
//    public Course getCourseById(int id) {
//        String sql = "SELECT * FROM Course WHERE Id = ?";
//        return template.queryForObject(sql, new Object[]{id}, new RowMapper<Course>() {
//            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Course course = new Course();
//                course.setId(rs.getInt("Id"));
//                course.setName(rs.getString("Name"));
//                course.setDescription(rs.getString("Description"));
//                course.setCredit(rs.getInt("Credit"));
//                return course;
//            }
//        });
//    }
//
//
//    public void deleteCourse(Course course) {
//        String sql="delete from Course where Id = ?";
//        template.update(sql,course.getId());
//
//    }
//    public void updateCourse(Course oldCourse,Course newCourse) {
//        String sql = "update Course set Name = ?, Description = ?, Credit = ? where Id = ?";
//        template.update(sql,newCourse.getName(),newCourse.getDescription(),newCourse.getCredit(),oldCourse.getId());
//
//    }
//
//}
