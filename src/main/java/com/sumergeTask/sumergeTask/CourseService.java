package com.sumergeTask.sumergeTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CourseService {
    private CourseRecommender courseRecommender;

    private JdbcTemplate template;

    @Autowired
    public CourseService(@Qualifier("firstCourseRecommender")CourseRecommender courseRecommender) {
        courseRecommender = courseRecommender;
    }
    @Autowired
    public void setCourseRecommender(@Qualifier("secondCourseRecommender")CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
    public JdbcTemplate getTemplate() {
        return template;
    }
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public void addCourse(Course course) {
        String sql="insert into Course values(?,?,?,?)";
        int rows =template.update(sql,course.getId(),course.getName(),course.getDescription(),course.getCredit());

        System.out.println(rows);
    }
    public List<Course> getCourses() {
        String sql="select * from Course";
        return template.query(sql, new RowMapper<Course>() {
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setName(rs.getString("Name"));
                course.setDescription(rs.getString("Description"));
                course.setCredit(rs.getInt("Credit"));
                return course;

            }
        });
    }

    public void deleteCourse(Course course) {
        String sql="delete from Course where Id = ?";
        template.update(sql,course.getId());

    }
    public void updateCourse(Course course,String name,String description,int credit) {
        String sql = "update Course set Name = ?, Description = ?, Credit = ? where Id = ?";
        template.update(sql,name,description,credit,course.getId());

    }

}
