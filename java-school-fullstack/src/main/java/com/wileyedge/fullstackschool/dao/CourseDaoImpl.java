package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.CourseMapper;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        try {
            jdbcTemplate.update(
                    "INSERT INTO COURSE (cid, courseCode , courseDesc, teacherId) VALUES (?, ?, ?, ?)", course.getCourseId(),
                    course.getCourseName(), course.getCourseDesc(), course.getTeacherId()
            );
            return course;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM course",
                    new CourseMapper()
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
	

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM course WHERE cid = ?",
                    new CourseMapper(),
                    id
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "UPDATE course SET courseCode = ?, courseDesc = ?, teacherId = ? WHERE cid = ?",
                    course.getCourseName(), course.getCourseDesc(), course.getTeacherId(), course.getCourseId()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "DELETE FROM course WHERE cid = ?",
                    id
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE

        try {
            jdbcTemplate.update(
                    "DELETE FROM course_student WHERE course_id = ?",
                    courseId
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }
}
