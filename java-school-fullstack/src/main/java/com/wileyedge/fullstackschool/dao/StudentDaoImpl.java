package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.StudentMapper;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        try {
            jdbcTemplate.update(
                    "INSERT INTO student (sid, fName, lName) VALUES (?, ?, ?)",
                    student.getStudentId(), student.getStudentFirstName(), student.getStudentLastName()
            );
            return student;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        try {
            return jdbcTemplate.query("SELECT * FROM student", new StudentMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM student WHERE sid = ?",
                    new StudentMapper(),
                    id
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "UPDATE student SET fName = ?, lName = ? WHERE sid = ?",
                    student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update("DELETE FROM student WHERE sid = ?", id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "INSERT INTO course_student (student_id, course_id) VALUES (?, ?)",
                    studentId, courseId
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            jdbcTemplate.update(
                    "DELETE FROM course_student WHERE student_id = ? AND course_id = ?",
                    studentId, courseId
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //YOUR CODE ENDS HERE
    }
}
